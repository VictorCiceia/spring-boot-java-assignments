package org.adaschool.api.controller;

import org.adaschool.api.repository.user.User;
import org.adaschool.api.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") final String id) {
        final Optional<User> user = this.usersService.findById(id);
        return ResponseEntity.ok(user.get());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.usersService.all());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        URI createdUserUri = URI.create("");
        return ResponseEntity.created(createdUserUri).body(this.usersService.save(user));
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") final String id, @RequestBody final User user) {
        return ResponseEntity.ok(this.usersService.update(user, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final String id) {
        this.usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}