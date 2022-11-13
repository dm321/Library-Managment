package com.dm.library.user.persistence;

import com.dm.library.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPersonId(UUID persondId);

    List<User> deleteByPersonId(UUID personId);

}
