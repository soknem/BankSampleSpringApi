package app.bank.feature.users;

import app.bank.domain.Role;
import app.bank.domain.User;
import app.bank.feature.roles.RoleRepository;
import app.bank.feature.users.dto.UserRequest;
import app.bank.feature.users.dto.UserResponse;
import app.bank.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private  final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserResponse createUser(UserRequest request) {
        System.out.println("hello");
        Set<Role> roles=new HashSet<>();
        for(var role:request.roles()){
            var roleObj=roleRepository.findByName(role).orElseThrow();
            roles.add(roleObj);
        }
        System.out.println("hi");
        User newUser=userMapper.requestToUser(request);
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return  userMapper.toUserResponse(new User());
    }
    @Override
    public List<UserResponse> getAllUser(){
        return userRepository.findAll()
                       .stream().map(userMapper::toUserResponse).toList();
    }


    @Override
    public UserResponse getUserById(String id) {
        var user=userRepository.findById(id).orElseThrow();
        return userMapper.toUserResponse(user);
    }

}
