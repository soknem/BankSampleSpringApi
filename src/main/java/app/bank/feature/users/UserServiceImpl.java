package app.bank.feature.users;

import app.bank.domain.Role;
import app.bank.domain.User;
import app.bank.feature.roles.RoleRepository;
import app.bank.feature.users.dto.UserRequest;
import app.bank.feature.users.dto.UserResponse;
import app.bank.feature.users.dto.UserUpdateRequest;
import app.bank.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
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
        if(userRepository.existsByUsername(request.username())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"username already exist");
        }
        if(userRepository.existsByEmail(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already token");
        }
        Set<Role> roles=new HashSet<>();
        for(var role:request.roles()){
            var roleObj=roleRepository.findByName(role).orElseThrow(
                    ()->new NoSuchElementException("role <\""+role+"\"> could not found")
            );
            roles.add(roleObj);
        }

        User newUser=userMapper.requestToUser(request);
        newUser.setIsBlocked(false);
        newUser.setIsDeleted(false);
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return  userMapper.toUserResponse(newUser);
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

    @Override
    public void deleteByUserId(String id) {
        var user =userRepository.findById(id).orElseThrow(()->new NoSuchElementException("There is no user Id ="+id));
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse updateUserById(String id, UserUpdateRequest userRequest) {



        var updateUser=userRepository.findById(id).orElseThrow(()->new NoSuchElementException("there is no user id = "+id));
        userMapper.updateUserFromRequest(updateUser, userRequest);
        return userMapper.toUserResponse(userRepository.save(updateUser));
    }

    @Override
    public UserResponse disableUser(String id) {
       int effectRows= userRepository.updateBlockedStatusById(id,true);
        if(effectRows>0){
            return userMapper.toUserResponse(userRepository.findById(id).orElse(null));
        }else {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND),"User with id="+id+"doesn't exist");
        }

    }

    @Override
    public UserResponse enableUser(String id) {
        int effectRows=userRepository.updateBlockedStatusById(id,false);
        if(effectRows>0){
            return userMapper.toUserResponse(userRepository.findById(id).orElse(null));
        }else {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND),"User with id="+id+"doesn't exist");
        }

    }
}
