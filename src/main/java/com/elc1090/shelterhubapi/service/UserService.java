package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.UserRegisterDTO;
import com.elc1090.shelterhubapi.model.User;
import com.elc1090.shelterhubapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public User findById(Long id) {
        Optional<User> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public UserDetails findDetailsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public void register(UserRegisterDTO data) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(data.password());

        User user = new User();
        mountObject(data, user);
        user.setPassword(encryptedPassword);

        this.save(user);
    }

    private User save(User user) {
        return repository.save(user);
    }

    public void update(UserRegisterDTO data) {
        User user = findById(data.id());
        mountObject(data, user);
        repository.save(user);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private void mountObject(UserRegisterDTO data, User user) {
        user.setName(data.name());
        user.setCpf(data.cpf());
        user.setPassword(data.password());
        user.setRole(data.role());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findDetailsByCpf(username);
    }
}
