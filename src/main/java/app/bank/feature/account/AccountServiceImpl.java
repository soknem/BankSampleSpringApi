package app.bank.feature.account;

import app.bank.feature.account.dto.AccountReponse;
import app.bank.feature.account.dto.AccountRequest;
import app.bank.feature.accounttype.AccountTypeRepository;
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
    private final AccountMapper mapper;
    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;

    @Override
    public List<AccountReponse> getAllAccounts() {
        return null;
    }

    @Override
    public AccountReponse createAccount(AccountRequest accountRequest) {
        var accountType = accountTypeRepository.findByName(accountRequest.accountType()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account Type " + accountRequest.accountType() + "is not valid"));

        var owner = userRepository.findById(accountRequest.userId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id = "+accountRequest.userId()+"not found"));


        return null;
    }

    @Override
    public AccountReponse findAccountById(String id) {
        return null;
    }

    @Override
    public AccountReponse findAccountByAccountNumber() {
        return null;
    }

    @Override
    public List<AccountReponse> findAccountByUserId() {
        return null;
    }
}
