package app.bank.init;

import app.bank.domain.AccountType;
import app.bank.domain.Role;
import app.bank.feature.accounttype.AccountTypeRepository;
import app.bank.feature.roles.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataInitializer {
    private  final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    @PostConstruct
    void rolesInit(){
        List<String> roles=List.of("ADMIN","STUFF","USER");
        for(var role: roles){
            var roleObj=new Role();
            roleObj.setName(role);
            roleRepository.save( roleObj);
        }
    }
    @PostConstruct
    void accountTypeInit(){
        List<AccountType> accountTypes=new ArrayList<>(){{
            add(new AccountType().setName("SAVING").setDescription("This is the account type that you gain interest when you saving your money in the bank"));
            add(new AccountType().setName("PAYROLLS").setDescription("this account to get paid by company monthly"));
            add(new AccountType().setName("CARD").setDescription("Allow you to create difference card for personal uses"));
        }};
        if(accountTypeRepository.findAll().isEmpty()){
            accountTypeRepository.saveAll(accountTypes);
        }
    }
}
