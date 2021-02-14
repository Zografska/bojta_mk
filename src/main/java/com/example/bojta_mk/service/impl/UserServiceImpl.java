package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.model.User;
import com.example.bojta_mk.model.enumerations.Role;
import com.example.bojta_mk.model.exeptions.InvalidArgumentsException;
import com.example.bojta_mk.model.exeptions.PasswordsDoNotMatchException;
import com.example.bojta_mk.model.exeptions.UsernameAlreadyExistsException;
import com.example.bojta_mk.repository.UserRepository;
import com.example.bojta_mk.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String email, String username, String password, String repeatPassword, String name, String surname, String phone, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }

        if (!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }

        if (this.userRepository.findByUsername(username).isPresent()) System.out.println(this.userRepository.findByUsername(username).get().getUsername());

        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User( email, username, passwordEncoder.encode(password), name, surname, phone, role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user =  this.userRepository.findById(s).orElseThrow(() -> new UsernameNotFoundException(s));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                Stream.of(new SimpleGrantedAuthority(user.getRole().toString())).collect(Collectors.toList()));
    }
}

