package app.bank.feature.account;

import app.bank.feature.account.dto.AccountResponse;
import app.bank.feature.account.dto.AccountRequest;

import java.util.List;

public interface AccountService {
    List<AccountResponse> getAllAccounts();
    AccountResponse createAccount(AccountRequest accountRequest);
    AccountResponse findAccountById(String id);

    AccountResponse findAccountByAccountNumber(String accountNumber);

    List<AccountResponse> findAccountByUserId(String userId);
}
