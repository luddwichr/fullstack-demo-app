package com.example.demo.user;

import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
interface UserRepository extends Repository<UserCredentials, String> {
    UserCredentials findByUsername(String username);

    void save(UserCredentials user);
}