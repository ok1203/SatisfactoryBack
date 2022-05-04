package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer Id) throws UserNotFoundException {
        Optional<User> result = repo.findById(Id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not found any users with id " + Id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null){
            throw new UserNotFoundException("Could not found any users with id " + id);
        }
        if (count == 0){
            throw new UserNotFoundException("Could not found any users with id " + id);
        }
        repo.deleteById(id);
    }
}
