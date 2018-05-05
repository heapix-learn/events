package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.UpdateResponseBo;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.dto.ChangePasswordDto;
import com.heapix.events.controller.dto.UserUpdateDto;
import com.heapix.events.persistence.model.User;

import java.util.List;

/**
 * @author mgergalov
 */
public interface UserService {

    CreateResponseBo addUser(User user) throws Exception;
    List<User> getAll();
    UserAdminBo findUser(Long id);
    void update(UserUpdateDto userUpdateDto, Long userId);
    void delete(Long id);
    List<UserAdminBo> getUnregisteredUsers();
    List<UserAdminBo> getRegisteredUsers();
    void allowRegistration(Long userId);
    User changePassword(ChangePasswordDto password, Long userId) throws Exception;

}
