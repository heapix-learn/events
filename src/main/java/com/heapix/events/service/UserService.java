package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.DeleteResponseBo;
import com.heapix.events.controller.bo.UpdateResponseBo;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.dto.ChangePasswordDto;
import com.heapix.events.controller.dto.RegistrationDto;
import com.heapix.events.controller.dto.UserUpdateDto;
import com.heapix.events.persistence.model.User;

import java.util.List;

/**
 * @author mgergalov
 */
public interface UserService {

    CreateResponseBo addUser(RegistrationDto user, Long role) throws Exception;
    List<User> getAll();
    UserAdminBo findUser(Long id);
    UserAdminBo findUser(String email);
    void update(UserUpdateDto userUpdateDto, Long userId);
    DeleteResponseBo delete(Long id);
    DeleteResponseBo blockUser(Long id);
    List<UserAdminBo> getUnregisteredUsers();
    List<UserAdminBo> getRegisteredUsers();
    User saveUserAdditionalInfo(String info, Long id);
    void allowRegistration(Long userId);
    User changePassword(ChangePasswordDto password, Long userId) throws Exception;

}
