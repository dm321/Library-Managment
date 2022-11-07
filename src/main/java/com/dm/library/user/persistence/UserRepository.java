package com.dm.library.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dm.library.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
