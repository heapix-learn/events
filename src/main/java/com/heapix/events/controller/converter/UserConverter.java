package com.heapix.events.controller.converter;

import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mgergalov
 */
@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface UserConverter {

    UserAdminBo toUserAdminBo(User user);
    List<UserAdminBo> toUserAdminBos(List<User> users);

}
