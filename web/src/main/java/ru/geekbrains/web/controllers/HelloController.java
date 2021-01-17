package ru.geekbrains.web.controllers;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.web.entities.User;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String sayHello(@RequestParam(name = "name") String name){
        return "Hello " + name;
    }

    @PostMapping
    public String createUser(@RequestBody(required = false) User user){
        return "New user " + user.getUsername() + " with password \"" + user.getPassword() + "\" was created\n";
    }

}
