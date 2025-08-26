package com.example.userauthservice.service;

import com.example.userauthservice.exceptions.InvalidTokenException;
import com.example.userauthservice.model.Token;
import com.example.userauthservice.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import com.example.userauthservice.exceptions.PasswordMisMatchException;
import com.example.userauthservice.exceptions.UserAlreadyExistsException;
import com.example.userauthservice.exceptions.UserNotExistsException;
import com.example.userauthservice.model.Role;
import com.example.userauthservice.model.User;
import com.example.userauthservice.repository.RoleRepository;
import com.example.userauthservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class DbAuthService implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    SecretKey secretKey;
    private static final long EXPIRATION_TIME = 10*60*60*1000; // 10 hours in milliseconds


    public  DbAuthService(
            UserRepository userRepository ,
            RoleRepository roleRepository ,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            SecretKey secretKey
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.secretKey= secretKey;

    }

    @Override
    public User signUp(User user) throws UserAlreadyExistsException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("ROLE_USER");
                    return roleRepository.save(newRole);
                });

        if(user.getRoleList() == null || user.getRoleList().isEmpty()){
            user.setRoleList(List.of(role));
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

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        // Generate a token for the user
        Map<String , Object> claims = new HashMap<>();
        claims.put("userId" , existingUser.get().getId());
        claims.put("email" , existingUser.get().getEmail());

        String jsonToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(existingUser.get().getEmail())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();


        Token token = new Token();
        token.setUser(existingUser.get());
        token.setTokenVal(jsonToken);
        token.setExpirationDate(expiryDate);
        return token;

    }

    @Override
    public User validateToken(String token) throws InvalidTokenException {
        if(token == null || token.isEmpty()){
            throw new InvalidTokenException("Token is null or empty");
        }
        Claims claims;
        try{
            claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        }
        catch (io.jsonwebtoken.ExpiredJwtException e){
            System.out.println("Token is expired: " + e.getMessage());
            return null;
        }
        catch(io.jsonwebtoken.JwtException e){
            System.out.println("Token is invalid: " + e.getMessage());
            return null;
        }

        String email = claims.get("email", String.class);
        if(email == null || email.isEmpty()){
            System.out.println("Email claim is missing in the token");
            return null;
        }
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            System.out.println("User with email " + email + " does not exist");
            return null;
        }
        return userOptional.get();
    }


//    private User validateNonJwtToken(String token) throws InvalidTokenException {
//
//        Optional<Token> tokenOptional = tokenRepository.findByTokenValAndExpirationDateAfter(token , new Date());
//        if(tokenOptional.isEmpty()){
//            throw new InvalidTokenException("Invalid or expired token: " + token);
//        }
//        Token validToken = tokenOptional.get();
//        User user = validToken.getUser();
//        if (user == null) {
//            throw new InvalidTokenException("No user associated with the token: " + token);
//        }
//        return user;
//    }
}
