package com.example.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud.entity.User;
import com.example.crud.repository.UserRepository;

@Service
public class UserService {

private final UserRepository repository;

public UserService(UserRepository repository) {
this.repository = repository;
}

public List<User> getAll(){
return repository.findAll();
}

public User save(User user){
return repository.save(user);
}

public void delete(Long id){
repository.deleteById(id);
}

}