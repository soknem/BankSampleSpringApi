package app.bank.feature.users;

import app.bank.feature.users.dto.UserRequest;
import app.bank.feature.users.dto.UserResponse;
import app.bank.feature.users.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    public List<UserResponse> getAllUser();
    public UserResponse getUserById(String id);

    void deleteByUserId(String id);

    UserResponse updateUserById(String id, UserUpdateRequest userRequest);

    UserResponse disableUser(String id);

    UserResponse enableUser(String id);
}
