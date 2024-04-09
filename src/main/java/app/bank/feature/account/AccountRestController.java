package app.bank.feature.account;

import app.bank.feature.account.dto.AccountResponse;
import app.bank.feature.account.dto.AccountRequest;
import app.bank.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static app.bank.utils.BaseResponse.ok;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    @PostMapping()
    @Operation(summary = "Create new account")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        return BaseResponse.<AccountResponse>createSuccess().setPayLoad(accountService.createAccount(request));
    }

    @GetMapping()
    @Operation(summary = "get all accounts")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<AccountResponse>> getAllAccounts() {
        return BaseResponse.<List<AccountResponse>>ok().setPayLoad(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "get account by id")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<AccountResponse> getAccountById(@Parameter(
            description = "Account Id",
            required = true,
            example = "ce4aa91c-bd26-4412-a0a3-e648f437f7eb") @PathVariable("id") String id) {
        return BaseResponse.<AccountResponse>ok().setPayLoad(accountService.findAccountById(id));
    }

    @GetMapping("/account-number/{id}")
    @Operation(summary = "get account by account number")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<AccountResponse> getAccountByAccountNumber(@Parameter(
            description = "Account Number",
            required = true,
            example = "string1") @PathVariable("id") String accountNumber) {
        return BaseResponse.<AccountResponse>ok().setPayLoad(accountService.findAccountByAccountNumber(accountNumber));
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "get account by account user Id")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<AccountResponse>> getAccountByUserId(@Parameter(
            description = "User Id",
            required = true,
            example = "2ab3cc70-66e3-4759-ad40-13d29445c3c5") @PathVariable("id") String userId) {
        return BaseResponse.<List<AccountResponse>>ok().setPayLoad(accountService.findAccountByUserId(userId));
    }
}
