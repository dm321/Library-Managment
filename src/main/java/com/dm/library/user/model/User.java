package com.dm.library.user.model;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.UUID;

@Entity
public class User extends AbstractEntity {

    private UUID personId;

    private String firstName;

    private String lastName;

    private String email;

    @PrePersist
    @Override
    public void prePersist() {
        super.prePersist();
        personId = UUID.randomUUID();
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "personId=" + personId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", id=" + id + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + '}';
    }
}
