package app.bank.feature.users;


import app.bank.feature.users.dto.UserRequest;
import app.bank.feature.users.dto.UserResponse;
import app.bank.mapper.UserMapper;
import app.bank.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserMapper userMapper;
    private final UserServiceImpl userService;

    @PostMapping()
    public BaseResponse<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        return BaseResponse.<UserResponse>createSuccess().setPayLoad(userService.createUser(userRequest));
    }

    @GetMapping("")
    @Operation(summary = "Get all user")
    public BaseResponse<List<UserResponse>> getAllUser() {
        return BaseResponse.<List<UserResponse>>ok()
                       .setPayLoad(userService.getAllUser());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get all user by id")
    public BaseResponse<UserResponse> getUerById(@Valid @PathVariable String id) {

        return BaseResponse.<UserResponse>ok()
                       .setPayLoad(userService.getUserById(id));
    }

    @PostMapping("/{id}")
    @Operation(summary = "Get all user by id")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<UserResponse> delete(@Valid @PathVariable String id) {

        return BaseResponse.<UserResponse>ok()
                       .setPayLoad(userService.getUserById(id));
    }

}
