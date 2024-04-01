package app.bank.feature.users;

import app.bank.domain.Role;
import app.bank.domain.User;
import app.bank.feature.roles.RoleRepository;
import app.bank.feature.users.dto.UserRequest;
import app.bank.feature.users.dto.UserResponse;
import app.bank.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private  final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private UserMapper userMapper;
    @Override
    public UserResponse createUser(UserRequest request) {
        Set<Role> roles=new HashSet<>();
        for(var role:request.roles()){
            var roleObj=roleRepository.findByName(role).orElseThrow();
            roles.add(roleObj);
        }
        User newUser=userMapper.requestToUser(request);
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return  userMapper.toUserResponse(new User());
    }
}
