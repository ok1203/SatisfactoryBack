package com.example.task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    public Long countById(Integer id);
}
