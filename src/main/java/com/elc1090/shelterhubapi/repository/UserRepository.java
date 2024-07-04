package com.elc1090.shelterhubapi.repository;

import com.elc1090.shelterhubapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByCpf(String cpf);

    User findByCpfAndShelter_Id(String cpf, Long shelterId);
}
