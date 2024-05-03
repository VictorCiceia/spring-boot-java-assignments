package org.adaschool.api.controller.user;

import org.adaschool.api.exception.UserNotFoundException;
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

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        URI createdUserUri = URI.create("");
        return ResponseEntity.created(createdUserUri).body(this.usersService.save(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.usersService.all());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") final String id) {
        final Optional<User> user = this.usersService.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException(id);
        return ResponseEntity.ok(user.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") final String id, @RequestBody final User user) {
        if (this.usersService.findById(id).isEmpty())
            throw new UserNotFoundException(id);
        return ResponseEntity.ok(this.usersService.update(user, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final String id) {
        if (this.usersService.findById(id).isEmpty())
            throw new UserNotFoundException(id);
        this.usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
