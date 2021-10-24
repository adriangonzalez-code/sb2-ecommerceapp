package com.smoothiemx.ecoomerceapp.shopmebackend.app.user;

import com.smoothiemx.ecommerceapp.shopmecommon.entity.Role;
import com.smoothiemx.ecommerceapp.shopmecommon.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testCreateUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userAdrian = new User("adrian@gmail.com", "1234", "Adrian", "Gonzalez");
        userAdrian.addRole(roleAdmin);

        User savedUser = repo.save(userAdrian);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void testCreateUserWithTwoRole() {
        User userRavi = new User("ravi@gmail.com", "ravi2020", "Ravi", "Kumar");
        Role roleEditor = new Role(3);
        Role roleAssitant = new Role(5);
        userRavi.addRole(roleEditor);
        userRavi.addRole(roleAssitant);

        User savedUser = repo.save(userRavi);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void testListAllUsers() {
        Iterable<User> userList = repo.findAll();
        userList.forEach(System.out::println);
    }

    @Test
    void testGetUserById() {
        Optional<User> user = repo.findById(1);
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    void testUpdateUserDetails() {
        User user = repo.findById(1).get();
        user.setEnabled(true);
        user.setEmail("name@gmail.com");

        User save = repo.save(user);
    }

    @Test
    void testUpdateUserRoles() {
        User user = repo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesperson = new Role(2);

        user.getRoles().remove(roleEditor);
        user.addRole(roleSalesperson);

        User save = repo.save(user);
    }

    @Test
    void testDeleteUser() {
        Integer userId = 2;
        repo.deleteById(userId);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "name@gmail.com";
        User user = repo.getUserByEmail(email);


        assertThat(user).isNotNull();
    }
}