package com.heapix.events.controller;

import com.heapix.events.config.security.UserAuth;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.dto.ChangePasswordDto;
import com.heapix.events.controller.dto.UserUpdateDto;
import com.heapix.events.persistence.model.enums.UserRole;
import com.heapix.events.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.heapix.events.controller.EventUtils.getUserId;

/**
 * @author mgergalov
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/unregistered")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator')")
    public List<UserAdminBo> getUnregisteredUsers() {
        return userService.getUnregisteredUsers();
    }

    @GetMapping("/registered")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator')")
    public List<UserAdminBo> getRegisteredUsers() {
        return userService.getRegisteredUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public UserAdminBo getUserInfo(@NotNull @PathVariable long id) throws NotFoundException {
        UserAdminBo bo = userService.findUser(id);
        return bo;
    }

    @GetMapping("/me")
    public UserAdminBo getUserInfo() throws NotFoundException {
        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserAdminBo bo = userService.findUser(currUser.getId());
        return bo;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public ResponseEntity updateUser(@NotEmpty @RequestBody UserUpdateDto userUpdateDto,
                                     @PathVariable long id) {
        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currUserRole = userService.findUser(currUser.getId()).getRole();
        if (currUserRole.equals(UserRole.SYSTEM_SUPER_ADMIN.getId())) {
            userService.update(userUpdateDto, id, userUpdateDto.getRole());
        } else if (currUserRole <= UserRole.SYSTEM_ADMIN_USER.getId() && userService.findUser(id).getRole() > 2) {
            userService.update(userUpdateDto, id, userUpdateDto.getRole());
        } else {
            userService.update(userUpdateDto, id, null);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator','Moderator')")
    public ResponseEntity blockUser(@PathVariable long id) {
        return new ResponseEntity(userService.blockUser(id), HttpStatus.OK);

    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('Administrator','Moderator', 'Member')")
    public ResponseEntity unsubscribe() {
        return new ResponseEntity(userService.delete(getUserId()), HttpStatus.OK);
    }

    @PutMapping("/password")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public ResponseEntity changePassword(@RequestBody ChangePasswordDto password) throws Exception {
        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changePassword(password, currUser.getId());
        return new ResponseEntity(HttpStatus.OK);
    }
}
