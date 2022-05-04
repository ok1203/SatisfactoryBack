package com.example;

import com.example.task.Task;
import com.example.task.TaskRepository;
import com.example.user.User;
import com.example.user.UserRepository;
import com.example.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TaskRepository repoTask;

    @GetMapping("/Api")
    public String getPage() {
        return "Empty!";
    }

    @GetMapping("/Api/users")
    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    @PostMapping("/Api/save")
    public List<User> save(@RequestBody User user) {
        user.setDateOfCreation(LocalDateTime.now().toString());
        repo.save(user);
        return (List<User>) repo.findAll();
    }

    @PutMapping("/Api/update/{id}")
    public List<User> update(@PathVariable Integer id, @RequestBody User user){
        user.setDateOfCreation(LocalDateTime.now().toString());
        User updatedUser = repo.findById(id).get();
        updatedUser.setPassword(user.getPassword());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        repo.save(updatedUser);
        return (List<User>) repo.findAll();
    }

    @DeleteMapping("/Api/delete/{id}")
    public List<User> delete(@PathVariable Integer id){
        User deletedUser = repo.findById(id).get();
        repo.delete(deletedUser);
        return (List<User>) repo.findAll();
    }

    @GetMapping("/Api/tasks")
    public List<Task> listAllTask() {
        return (List<Task>) repoTask.findAll();
    }

    @PostMapping("/Api/saveTask")
    public List<Task> saveTask(@RequestBody Task task) {
        repoTask.save(task);
        return (List<Task>) repoTask.findAll();
    }

    @PutMapping("/Api/updateTask/{id}")
    public List<Task> updateTask(@PathVariable Integer id, @RequestBody Task task){
        Task updatedTask = repoTask.findById(id).get();
        updatedTask.setContent(task.getContent());
        updatedTask.setStatus(task.isStatus());
        updatedTask.setDeadline(task.getDeadline());
        updatedTask.setUser(task.getUser());
        repoTask.save(updatedTask);
        return (List<Task>) repoTask.findAll();
    }

    @DeleteMapping("/Api/delete/{id}")
    public List<Task> deleteTask(@PathVariable Integer id){
        Task deletedTask = repoTask.findById(id).get();
        repoTask.delete(deletedTask);
        return (List<Task>) repoTask.findAll();
    }

}
