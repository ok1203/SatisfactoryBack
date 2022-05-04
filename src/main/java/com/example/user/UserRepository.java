package com.example.user;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
}
