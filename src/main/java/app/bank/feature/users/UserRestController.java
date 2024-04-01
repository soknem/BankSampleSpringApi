package app.bank.feature.users;



import app.bank.feature.users.dto.UserRequest;
import app.bank.feature.users.dto.UserResponse;
import app.bank.mapper.UserMapper;
import app.bank.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserMapper userMapper;
    private  final UserServiceImpl userService;

    public BaseResponse<UserResponse> registerUser(@RequestBody UserRequest userRequest){
        return BaseResponse.<UserResponse>createSuccess().setPayLoad(userService.createUser(userRequest));
    }

}
