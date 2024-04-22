package app.bank.feature.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthRestController {
    @PostMapping("/login")
    public String login(){
        return "Login success";

    }
    @PostMapping("/refreshToken")
    public String refreshToken(){
        return "refresh token";
    }
    @PostMapping("/register")
    public String register(){
        return "register successfully";
    }
}
