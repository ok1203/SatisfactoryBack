package com.example.task;


import com.example.user.User;
import com.example.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired private TaskRepository repo;


    public List<Task> listAll() {
        return (List<Task>) repo.findAll();
    }

    public List<Task> listUserAll(Integer Id) {
        List<Task> tasks = (List<Task>) repo.findAll();
        List<Task> user = new ArrayList<>();

        for(int i = 0 ; i < tasks.size(); i++){
            if(tasks.get(i).getUser().getId() == Id){
                user.add(tasks.get(i));
            }
        }

        return user;
    }

    public void save(Task task) {
        repo.save(task);
    }

    public Task get(Integer Id) throws TaskNotFoundException {
        Optional<Task> result = repo.findById(Id);
        if(result.isPresent()){
            return result.get();
        }
        throw new TaskNotFoundException("Could not found any taska with id " + Id);
    }

    public void delete(Integer id) throws TaskNotFoundException {
        Long count = repo.countById(id);
        if (count == null){
            throw new TaskNotFoundException("Could not found any users with id " + id);
        }
        if (count == 0){
            throw new TaskNotFoundException("Could not found any users with id " + id);
        }
        repo.deleteById(id);
    }
}
