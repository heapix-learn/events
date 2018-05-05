package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.converter.UserConverter;
import com.heapix.events.controller.dto.ChangePasswordDto;
import com.heapix.events.controller.dto.UserUpdateDto;
import com.heapix.events.persistence.model.User;
import com.heapix.events.persistence.model.enums.UserRole;
import com.heapix.events.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mgergalov
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserConverter userConverter;


    @Override
    public CreateResponseBo addUser(User user) throws Exception {
        User alreadyExist = userRepository.findByEmail(user.getEmail());
        if (alreadyExist == null) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole(UserRole.MEMBER_USER.getId());
            User response = userRepository.save(user);
            return new CreateResponseBo(response.getId());
        } else throw new Exception("user already registered");
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserAdminBo findUser(Long userId) {
        User entity = userRepository.getOne(userId);
        return userConverter.toUserAdminBo(entity);
    }

    @Override
    public void update(UserUpdateDto userUpdateDto, Long userId) {
        User entity = userRepository.getOne(userId);
        entity.setEmail(userUpdateDto.getEmail());
        entity.setFirstName(userUpdateDto.getFirstName());
        entity.setLastName(userUpdateDto.getLastName());
        entity.setPhone1(userUpdateDto.getPhone1());
        entity.setPhone2(userUpdateDto.getPhone2());
        userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(userRepository.getOne(id));
    }

    @Override
    public List<UserAdminBo> getUnregisteredUsers() {
        List<User> entities = userRepository.findByRoleEquals(UserRole.ANONYMOUS_USER.getId());
        List<UserAdminBo> userAdminBos = userConverter.toUserAdminBos(entities);
        return userAdminBos;
    }

    @Override
    public List<UserAdminBo> getRegisteredUsers() {
        List<User> entities = userRepository.findByRoleIsNot(UserRole.ANONYMOUS_USER.getId());
        List<UserAdminBo> userAdminBos = userConverter.toUserAdminBos(entities);
        return userAdminBos;
    }

    @Override
    public void allowRegistration(Long userId) {
        User entity = userRepository.getOne(userId);
        entity.setRole(UserRole.MEMBER_USER.getId());
    }

    @Override
    public User changePassword(ChangePasswordDto password, Long userId) throws Exception {
        User user = userRepository.getOne(userId);
        if(!encoder.encode(password.getOldPassword()).equals(user.getPassword())) {
            throw new Exception("current password is invalid");
        }
        if(!password.getNewPassword().equals(password.getOldPassword())){
            throw new Exception("Password does not match the confirm password");
        }
        user.setPassword(encoder.encode(password.getNewPassword()));
        return userRepository.save(user);
    }
}
