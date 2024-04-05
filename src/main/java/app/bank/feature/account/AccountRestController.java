package app.bank.feature.account;

import app.bank.feature.account.dto.AccountReponse;
import app.bank.feature.account.dto.AccountRequest;
import app.bank.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;
    @PostMapping
    public BaseResponse<AccountReponse> createAccount(@RequestBody AccountRequest request){
        return BaseResponse.<AccountReponse>createSuccess().setPayLoad(accountService.createAccount(request));
    }
}
