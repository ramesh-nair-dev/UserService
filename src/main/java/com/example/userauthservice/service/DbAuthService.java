package com.example.userauthservice.service;

import com.example.userauthservice.exceptions.InvalidTokenException;
import com.example.userauthservice.model.Token;
import com.example.userauthservice.repository.TokenRepository;
import org.antlr.v4.runtime.misc.Pair;
import com.example.userauthservice.exceptions.PasswordMisMatchException;
import com.example.userauthservice.exceptions.UserAlreadyExistsException;
import com.example.userauthservice.exceptions.UserNotExistsException;
import com.example.userauthservice.model.Role;
import com.example.userauthservice.model.User;
import com.example.userauthservice.repository.RoleRepository;
import com.example.userauthservice.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DbAuthService implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;

    public  DbAuthService(
            UserRepository userRepository ,
            RoleRepository roleRepository ,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            TokenRepository tokenRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;

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
    public Token login(User user) throws UserNotExistsException, PasswordMisMatchException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isEmpty()){
            throw new UserNotExistsException("User with email " + user.getEmail() + " does not exist");
        }

        String existingPassword = existingUser.get().getPassword();

        if(!bCryptPasswordEncoder.matches(user.getPassword(), existingPassword)){
            throw new PasswordMisMatchException("Password does not match for user with email " + user.getEmail());
        }

        // Generate a token for the user
        Token token = new Token();
        token.setUser(existingUser.get());
        token.setTokenVal(RandomStringUtils.randomAlphanumeric(128));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dateAfter30Days = calendar.getTime();
        token.setExpirationDate(dateAfter30Days);
        return tokenRepository.save(token);

    }

    @Override
    public User validateToken(String token) throws InvalidTokenException {
        Optional<Token> tokenOptional = tokenRepository.findByTokenValAndExpirationDateAfter(token , new Date());
        if(tokenOptional.isEmpty()){
            throw new InvalidTokenException("Invalid or expired token: " + token);
        }
        Token validToken = tokenOptional.get();
        User user = validToken.getUser();
        if (user == null) {
            throw new InvalidTokenException("No user associated with the token: " + token);
        }
        return user;
    }
}
