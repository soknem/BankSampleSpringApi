package app.bank.feature.account;

import app.bank.feature.account.dto.AccountReponse;
import app.bank.feature.account.dto.AccountRequest;

import java.util.List;

public interface AccountService {
    List<AccountReponse> getAllAccounts();
    AccountReponse createAccount(AccountRequest accountRequest);
    AccountReponse findAccountById(String id);
    AccountReponse findAccountByAccountNumber();
    List<AccountReponse> findAccountByUserId();
}
