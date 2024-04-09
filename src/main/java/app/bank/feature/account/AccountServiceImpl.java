package app.bank.feature.account;

import app.bank.domain.Account;
import app.bank.domain.UserAccount;
import app.bank.feature.account.dto.AccountResponse;
import app.bank.feature.account.dto.AccountRequest;
import app.bank.feature.accounttype.AccountTypeRepository;
import app.bank.feature.useraccount.UserAccountRepository;
import app.bank.feature.users.UserRepository;
import app.bank.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    public List<AccountResponse> getAllAccounts() {
        return userAccountRepository.findAll().stream().map(accountMapper::mapUserAccountToAccountResponse).toList();
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        var accountType = accountTypeRepository.findByName(accountRequest.accountType()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account Type " + accountRequest.accountType() + "is not valid"));

        var owner = userRepository.findById(accountRequest.userId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id = " + accountRequest.userId() + "not found"));

        if (userAccountRepository.countUserAccountsByUserId(accountRequest.userId()) >= 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id = " + accountRequest.userId() + "already has 5 accounts");
        }
        Account account = accountMapper.requestToEntity(accountRequest);
        account.setAccountType(accountType);
        UserAccount userAccount = new UserAccount().setAccount(account).setUser(owner).setIsDisable(false);
        userAccountRepository.save(userAccount);
        return accountMapper.mapUserAccountToAccountResponse(userAccount);
    }

    @Override
    public AccountResponse findAccountById(String id) {
        var userAccount = userAccountRepository.findByAccount_Id(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id = " + id + " not found"));

        return accountMapper.mapUserAccountToAccountResponse(userAccount);
    }

    @Override
    public AccountResponse findAccountByAccountNumber(String accountNumber) {
        var accountByAccountNumber = userAccountRepository.findByAccount_AccountNumber(accountNumber)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Account with account number = " + accountNumber + " not exist")
                );
        return accountMapper.mapUserAccountToAccountResponse(accountByAccountNumber);
    }

    @Override
    public List<AccountResponse> findAccountByUserId(String userId) {
        var accountUserId = userAccountRepository.findByUser_Id(userId);
        return accountUserId.stream()
                .map(accountMapper::mapUserAccountToAccountResponse).toList();
    }
}
