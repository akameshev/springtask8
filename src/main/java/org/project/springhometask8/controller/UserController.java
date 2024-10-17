package org.project.springhometask8.controller;

import lombok.AllArgsConstructor;
import org.project.springhometask8.aspect.TrackUserAction;
import org.project.springhometask8.model.User;
import org.project.springhometask8.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @TrackUserAction
    public ResponseEntity<List<User>> getAllUsers() {
        Optional<List<User>> users = Optional.ofNullable(userService.getAllUsers());
        if (users.isPresent()) {
            return ResponseEntity.ok(users.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    @TrackUserAction
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = Optional.ofNullable(userService.getUserById(id));
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @TrackUserAction
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    @TrackUserAction
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = Optional.ofNullable(userService.getUserById(id));
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userService.updateUser(id,userToUpdate);
            return ResponseEntity.ok(userToUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @TrackUserAction
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = Optional.ofNullable(userService.getUserById(id));
        if (userOptional.isPresent()) {
            User userToDelete = userOptional.get();
        }
        return ResponseEntity.notFound().build();
    }
}
