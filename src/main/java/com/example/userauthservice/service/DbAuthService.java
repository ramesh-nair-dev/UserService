package com.example.userauthservice.service;

import org.antlr.v4.runtime.misc.Pair;
import com.example.userauthservice.exceptions.PasswordMisMatchException;
import com.example.userauthservice.exceptions.UserAlreadyExistsException;
import com.example.userauthservice.exceptions.UserNotExistsException;
import com.example.userauthservice.model.Role;
import com.example.userauthservice.model.User;
import com.example.userauthservice.repository.RoleRepository;
import com.example.userauthservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DbAuthService implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public  DbAuthService(UserRepository userRepository , RoleRepository roleRepository , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    @Override
    public User signUp(User user) throws UserAlreadyExistsException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
        if(user.getRoleList() == null){
            List<Role> roleList = new ArrayList<>();
            Role role = new Role();
            role.setName("ROLE_USER");
            roleList.add(role);
            user.setRoleList(roleList);
            roleRepository.save(role);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Pair<User, String> login(User user) throws UserNotExistsException, PasswordMisMatchException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isEmpty()){
            throw new UserNotExistsException("User with email " + user.getEmail() + " does not exist");
        }

        String existingPassword = existingUser.get().getPassword();
        if(!existingPassword.equals(user.getPassword())){
            throw new PasswordMisMatchException("Password does not match for user with email " + user.getEmail());
        }

        return new Pair<>(existingUser.get(), "token-placeholder"); // Replace with actual token generation logic
    }
}
