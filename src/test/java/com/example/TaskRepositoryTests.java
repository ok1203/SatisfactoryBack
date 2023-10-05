package com.example;

import com.example.task.Task;
import com.example.task.TaskRepository;
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
public class TaskRepositoryTests {
    @Autowired private TaskRepository repo;
    @Autowired private UserRepository repoUser;

    @Test
    public void testAddNew() {
        Task task = new Task();
        Optional<User> user = repoUser.findById(1);
        task.setUser(user.get());
        task.setContent("task1");
        task.setDeadline("12.34.56");
        task.setStatus(false);

        Task savedTask = repo.save(task);

        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId());

    }

    @Test
    public void testAll() {
        Iterable<Task> tasks = repo.findAll();
        Assertions.assertThat(tasks).hasSizeGreaterThan(0);

        for(Task task : tasks) {
            System.out.println(task);
        }
    }

//    @Test
//    public void testUpdate() {
//        Integer taskId = 1;
//        Optional<Task> optionalTask = repo.findById(taskId);
//        Task task = optionalTask.get();
//        task.setContent("OMARIONWTF");
//        repo.save(task);
//
//        Task updatedTask = repo.findById(taskId).get();
//        Assertions.assertThat(updatedTask.getContent()).isEqualTo("OMARIONWTF");
//    }
//
//    @Test
//    public void testGet(){
//        Integer taskId = 1;
//        Optional<Task> optionalTask = repo.findById(taskId);
//        Assertions.assertThat(optionalTask).isPresent();
//        System.out.println(optionalTask.get());
//    }
//
//    @Test
//    public void testDelete() {
//        Integer taskId = 1;
//        repo.deleteById(taskId);
//
//        Optional<Task> optionalTask = repo.findById(taskId);
//        Assertions.assertThat(optionalTask).isNotPresent();
//    }
}
