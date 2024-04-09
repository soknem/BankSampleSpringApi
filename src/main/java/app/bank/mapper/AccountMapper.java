package app.bank.mapper;

import app.bank.domain.Account;
import app.bank.domain.User;
import app.bank.domain.UserAccount;
import app.bank.feature.account.dto.AccountResponse;
import app.bank.feature.account.dto.AccountRequest;
import app.bank.feature.account.dto.AccountResponse;
import app.bank.feature.users.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AccountMapper {
    //    AccountResponse toUserResponse(Account account);
    @Mapping(target = "accountType", ignore = true)
    Account requestToEntity(AccountRequest request);

    @Mapping(target = "id", source = "userAccount.account.id")
    @Mapping(target = "accountNumber", source = "userAccount.account.accountNumber")
    @Mapping(target = "accountName", source = "userAccount.account.accountName")
    @Mapping(target = "accountBalance", source = "userAccount.account.accountBalance")
    @Mapping(target = "user", source = "userAccount.user", qualifiedByName = "toUserResponse")
    @Mapping(target = "accountType", source = "userAccount.account.accountType.name")
    AccountResponse mapUserAccountToAccountResponse(UserAccount userAccount);
}
