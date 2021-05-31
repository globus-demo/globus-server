package com.globus.demo.repository;

import com.globus.demo.model.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
    User findUserById(Integer id);
}
