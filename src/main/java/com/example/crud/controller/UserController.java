package com.example.crud.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.crud.entity.User;
import com.example.crud.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

private final UserService service;

public UserController(UserService service) {
this.service = service;
}

@GetMapping
public List<User> getAll(){
return service.getAll();
}


@PostMapping
public User save(@RequestBody User user){
return service.save(user);

}

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id){
service.delete(id);
}

}
