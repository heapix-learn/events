package com.heapix.events.controller.converter;

import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.persistence.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author mgergalov
 */
@Component
public class UserConverterImpl implements UserConverter {
    public UserConverterImpl() {
    }

    public UserAdminBo toUserAdminBo(User user) {
        if (user == null) {
            return null;
        } else {
            UserAdminBo userAdminBo = new UserAdminBo();
            userAdminBo.setId(user.getId());
            userAdminBo.setAnnotations(user.getAnnotations());
            userAdminBo.setEmail(user.getEmail());
            userAdminBo.setFirstName(user.getFirstName());
            userAdminBo.setLastName(user.getLastName());
            userAdminBo.setPhone1(user.getPhone1());
            userAdminBo.setPhone2(user.getPhone2());
            userAdminBo.setRole(user.getRole());
            return userAdminBo;
        }
    }

    public List<UserAdminBo> toUserAdminBos(List<User> users) {
        if (users == null) {
            return null;
        } else {
            List<UserAdminBo> list = new ArrayList();
            Iterator var3 = users.iterator();

            while(var3.hasNext()) {
                User user = (User)var3.next();
                list.add(this.toUserAdminBo(user));
            }

            return list;
        }
    }
}
