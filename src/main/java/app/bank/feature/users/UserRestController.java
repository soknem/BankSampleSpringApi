package app.bank.feature.users;


import app.bank.domain.User;
import app.bank.feature.users.dto.UserRequest;
import app.bank.feature.users.dto.UserResponse;
import app.bank.feature.users.dto.UserUpdateRequest;
import app.bank.mapper.UserMapper;
import app.bank.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserMapper userMapper;
    private final UserServiceImpl userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register new user"
            , requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = UserRequest.class),
                    examples = @ExampleObject(value = """
                            {
                              "username": "sokkhann",
                              "fullName": "string",
                              "gender": "male",
                              "pin": "898989",
                              "email": "sokkhann@gmail.com",
                              "password": "string",
                              "profileImage": "string",
                              "phoneNumber": "string",
                              "cityOrProvince": "string",
                              "khanOrDistrict": "string",
                              "sangkatOrCommune": "string",
                              "employeeType": "string",
                              "companyName": "string",
                              "mainSourceOfIncome": "string",
                              "monthlyIncomeRange": 0,
                              "studentCardId": "string",
                              "roles": [
                                "ADMIN","STUFF"
                              ]
                            }
                                                        
                                                        
                            """)

            )
    )
    )
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponse<UserResponse> deleteUserById(@PathVariable String id) {
        userService.deleteByUserId(id);
        return BaseResponse.ok();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "update user by id")
    public BaseResponse<UserResponse> updateUserById(@PathVariable String id, @RequestBody UserUpdateRequest userRequest) {
        return BaseResponse.<UserResponse>ok()
                .setPayLoad(userService.updateUserById(id, userRequest));
    }

    @PatchMapping("/{id}/disable")
    public BaseResponse<UserResponse> disableUser(@PathVariable String id) {
        return BaseResponse.<UserResponse>ok()
                .setPayLoad(userService.disableUser(id));
    }

    @PatchMapping("/{id}/enable")
    public BaseResponse<UserResponse> enableUser(@PathVariable String id) {
        return BaseResponse.<UserResponse>ok()
                .setPayLoad(userService.enableUser(id));
    }


}
