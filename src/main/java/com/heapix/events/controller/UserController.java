package com.heapix.events.controller;

import com.heapix.events.config.security.UserAuth;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.dto.ChangePasswordDto;
import com.heapix.events.controller.dto.UserUpdateDto;
import com.heapix.events.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public ResponseEntity updateUser(@NotEmpty @RequestBody UserUpdateDto userUpdateDto,
                                      @PathVariable long id) {
        userService.update(userUpdateDto, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator','Moderator', 'Member')")
    public void removeUser(@PathVariable long id) {
        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currUser.getId().equals(userService.findUser(id).getId())){
            userService.delete(id);
        }
    }

    @PutMapping("/password")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public ResponseEntity changePassword(@RequestBody ChangePasswordDto password) throws Exception {
        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changePassword(password, currUser.getId());
        return new ResponseEntity(HttpStatus.OK);
    }
}
