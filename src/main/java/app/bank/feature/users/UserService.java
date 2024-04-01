package app.bank.feature.users;

import app.bank.feature.users.dto.UserRequest;
import app.bank.feature.users.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
}
