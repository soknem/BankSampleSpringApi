package app.bank.mapper;

import app.bank.domain.User;
import app.bank.feature.users.dto.UserRequest;
import app.bank.feature.users.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles",ignore = true)
    UserResponse toUserResponse(User user);
    @Mapping(target = "roles",ignore = true)
    User requestToUser(UserRequest userRequest);
}
