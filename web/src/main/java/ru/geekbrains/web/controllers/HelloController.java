package ru.geekbrains.web.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String sayHello(@RequestParam(name = "name") String name){
        return "Hello " + name;
    }

    @PostMapping
    public String createUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password){
        return "New user " + " with password \"" + password + "\" was created";
    }

}
