package com.example;

import com.example.user.User;
import com.example.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

//    @Test
//    public void testAddNew() {
//        User user = new User();
//        user.setPhoneNumber("87762472863");
//        user.setDateOfCreation(LocalDateTime.now().toString());
//        user.setUsername("hiruko");
//        user.setFirstName("Adilet");
//        user.setLastName("Toktybay");
//        user.setPassword("12345678");
//
//        User savedUser = repo.save(user);
//
//        Assertions.assertThat(savedUser).isNotNull();
//        Assertions.assertThat(savedUser.getId());
//
//    }

    @Test
    public void testAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("OMARIONWTF");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("OMARIONWTF");
    }

    @Test
    public void testGet(){
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

//    @Test
//    public void testDelete() {
//        Integer userId = 16;
//        repo.deleteById(userId);
//
//        Optional<User> optionalUser = repo.findById(userId);
//        Assertions.assertThat(optionalUser).isNotPresent();
//    }
}
