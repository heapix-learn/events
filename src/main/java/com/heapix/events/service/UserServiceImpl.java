package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.DeleteResponseBo;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.converter.UserConverter;
import com.heapix.events.controller.dto.ChangePasswordDto;
import com.heapix.events.controller.dto.RegistrationDto;
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
    public CreateResponseBo addUser(RegistrationDto user, Long role) throws Exception {
        User alreadyExist = userRepository.findByEmail(user.getEmail());
        if (alreadyExist == null) {
            User newUser = new User();
            newUser.setPassword(encoder.encode(user.getPassword()));
            newUser.setRole(role);
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setInputs(user.getInputs());
            newUser.setInfoProvided(false);
            User response = userRepository.save(newUser);
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
    public UserAdminBo findUser(String email) {
        User entity = userRepository.findByEmail(email);
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
        entity.setAnnotations(userUpdateDto.getAnnotations());
        userRepository.save(entity);
    }

    @Override
    public DeleteResponseBo blockUser(Long id) {
        User entity = userRepository.getOne(id);
        entity.setRole(UserRole.ANONYMOUS_USER.getId());
        User response = userRepository.save(entity);
        return new DeleteResponseBo(response.getId());
    }

    @Override
    public DeleteResponseBo delete(Long id) {
        userRepository.deleteById(id);
        return new DeleteResponseBo(id);
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
    public User saveUserAdditionalInfo(String info, Long id) {
        User entity = userRepository.getOne(id);
        entity.setInputs(info);
        entity.setInfoProvided(true);
       return userRepository.save(entity);
    }

    @Override
    public void allowRegistration(Long userId) {
        User entity = userRepository.getOne(userId);
        entity.setRole(UserRole.MEMBER_USER.getId());
        userRepository.save(entity);
    }

    @Override
    public User changePassword(ChangePasswordDto password, Long userId) throws Exception {
        User user = userRepository.getOne(userId);
        if(!encoder.matches(password.getOldPassword(), user.getPassword())) {
            throw new Exception("current password is invalid");
        }
        if(!password.getNewPassword().equals(password.getConfirmPassword())){
            throw new Exception("Password does not match the confirm password");
        }
        user.setPassword(encoder.encode(password.getNewPassword()));
        return userRepository.save(user);
    }
}
