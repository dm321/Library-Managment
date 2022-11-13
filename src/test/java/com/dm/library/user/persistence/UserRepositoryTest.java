package com.dm.library.user.persistence;

import com.dm.library.user.common.AbstractUserTest;
import com.dm.library.user.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest extends AbstractUserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByPersonId_Test_Positive() {
        User user = getInstanceOfUser(false);

        user = userRepository.save(user);
        User user2 = userRepository.findByPersonId(user.getPersonId());

        assertThat(user2).isNotNull();
    }

    @Test
    void userPrePersist_Test()
    {
        User user = getInstanceOfUser(false);
        user = userRepository.save(user);

        assertThat(user.getPersonId()).isNotNull();
        assertThat(user.getCreatedOn()).isNotNull();
        assertThat(user.getUpdatedOn()).isNotNull();
    }

    @Test
    void findByPersonId_Test_Negative() {
        ;
        User user = userRepository.findByPersonId(UUID.fromString(PERSONID));

        assertThat(user).isNull();
    }

    @Test
    void userPreUpdate_Test()
    {
        User user = getInstanceOfUser(false);
        user = userRepository.save(user);

        LocalDateTime createdOn = user.getUpdatedOn();
        user.setEmail("prepersist@test.com");
        userRepository.flush();

        LocalDateTime updatedOn = user.getUpdatedOn();
        assertThat(updatedOn).isNotNull();
        assertThat(updatedOn).isNotEqualTo(createdOn);

    }

    @Test
    void userDeleteByPersonId_Porisitve_Test()
    {

        User user = getInstanceOfUser(false);
        userRepository.save(user);

        List<User> deletedUsers = userRepository.deleteByPersonId(user.getPersonId());

        assertThat(deletedUsers.size()).isOne();
        assertThat(deletedUsers.get(0)).isEqualTo(user);

    }

    @Test
    void userDeleteByPersonId_Negative()
    {
        List<User> deletedUsers = userRepository.deleteByPersonId(UUID.fromString(PERSONID));
        assertThat(deletedUsers.size()).isZero();
    }
}
