package com.dm.library.user.persistence;

import com.dm.library.user.common.AbstractUserTest;
import com.dm.library.user.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
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
    void userPrePersist_Test() {
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
    void userPreUpdate_Test() {
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
    void userDeleteByPersonId_Porisitve_Test() {

        User user = getInstanceOfUser(false);
        userRepository.save(user);

        List<User> deletedUsers = userRepository.deleteByPersonId(user.getPersonId());

        assertThat(deletedUsers.size()).isOne();
        assertThat(deletedUsers.get(0)).isEqualTo(user);

    }

    @Test
    void userDeleteByPersonId_Negative() {
        List<User> deletedUsers = userRepository.deleteByPersonId(UUID.fromString(PERSONID));
        assertThat(deletedUsers.size()).isZero();
    }

    @Test
    void findAllTest_Positive() {
        int numberOfUsers = 11;
        int intnumberOfTotalPages = 2;
        int pageSize = 10;
        int pageNumber= 0;
        User userCreated = insertUserAndReturnLast(numberOfUsers);
        LocalDateTime lastUserCreationTime = userCreated.getCreatedOn();

        PageRequest pageRequest = PageRequest.of(0, pageSize, Sort.by("id")
                .ascending());

        Page<User> pagedUsers1 = userRepository.findAll(pageRequest);

        pageRequest= PageRequest.of(1,pageSize, Sort.by("id"));

        Page<User> pagedUsers2 = userRepository.findAll(pageRequest);

        assertThat(pagedUsers1.getTotalPages()).isEqualTo(intnumberOfTotalPages);
        assertThat(pagedUsers1.getTotalElements()).isEqualTo(numberOfUsers);
        assertThat(pagedUsers1.getSize()).isEqualTo(pageSize);
        assertThat(pagedUsers1.get().noneMatch(user -> user.getCreatedOn().isAfter(lastUserCreationTime))).isTrue();

        assertThat(pagedUsers2.stream().count()).isEqualTo(1);
        assertThat(pagedUsers2.isLast()).isTrue();
        assertThat(pagedUsers2.get().findFirst().get().getId()).isEqualTo(userCreated.getId());
    }

    private User insertUserAndReturnLast(int numberOfUser)
    {
        User user = null;
        for(int i=0; i<numberOfUser; i++)
        {
            user = getInstanceOfUser(false);
            userRepository.save(user);
        }
        return user;
    }
}
