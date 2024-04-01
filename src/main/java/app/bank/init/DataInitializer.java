package app.bank.init;

import app.bank.domain.Role;
import app.bank.feature.roles.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DataInitializer {
    private  final RoleRepository roleRepository;
    void rolesInit(){
        List<String> roles=List.of("ADMIN","STUFF","USER");
        for(var role: roles){
            var roleObj=new Role();
            roleObj.setName(role);
            roleRepository.save( roleObj);
        }
    }

}
