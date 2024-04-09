package app.bank.mapper;

import app.bank.domain.Role;
import app.bank.domain.User;
import app.bank.feature.users.dto.UserRequest;
import app.bank.feature.users.dto.UserResponse;
import app.bank.feature.users.dto.UserUpdateRequest;
import app.bank.utils.BaseResponse;
import org.mapstruct.*;
import org.springframework.http.HttpStatus;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Named("toUserResponse")
    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    User requestToUser(UserRequest userRequest);

    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

//    @Mapping(target = "username", source = "username",
//            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
//            , defaultExpression = "java(user.getUsername())")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "roles", ignore = true)
    void updateUserFromRequest(@MappingTarget User user, UserUpdateRequest userRequest);


}
