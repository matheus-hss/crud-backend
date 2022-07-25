package com.example.crud.service;

import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userModel = this.userRepository.findByEmail(username);

        if(userModel.isEmpty()) throw new UsernameNotFoundException("User not found");

        return new org.springframework.security.core.userdetails.User(userModel.get().getEmail(), userModel.get().getPassword(),
                true, true, true, true,
                    userModel.get().getRoles());
    }

    @Transactional
    public User save(User user){
        return this.userRepository.save(user);
    }

    public Optional<User> findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public Optional<User> findByCpf(String cpf){
        return this.userRepository.findByCpf(cpf);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
