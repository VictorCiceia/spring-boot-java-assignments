package org.adaschool.api.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Usuarios", description = "Endpoints para trabajar con Usuarios ")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Obtener por ID", description = "Obtener un Usuario por el atributo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = User.class)))}
    )
    public ResponseEntity<User> findById(@PathVariable("id") final String id) {
        final Optional<User> user = this.usersService.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException(id);
        return ResponseEntity.ok(user.get());
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Obtener todos los Usuarios", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = User.class)))}
    )
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.usersService.all());
    }

    @PostMapping
    @Operation(summary = "Crear", description = "Crear un Usuario nuevo", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = User.class)))}
    )
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        URI createdUserUri = URI.create("");
        return ResponseEntity.created(createdUserUri).body(this.usersService.save(user));
    }

    @PutMapping("{id}")
    @Operation(summary = "Actualizar", description = "Actualizar un Usuario por el atributo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = User.class)))}
    )
    public ResponseEntity<User> updateUser(@PathVariable("id") final String id, @RequestBody final User user) {
        if (this.usersService.findById(id).isEmpty())
            throw new UserNotFoundException(id);
        return ResponseEntity.ok(this.usersService.update(user, id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar", description = "Eliminar un Usuario por el atributo ID", responses = {
            @ApiResponse(responseCode = "202", description = "Operación exitosa")}
    )
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final String id) {
        if (this.usersService.findById(id).isEmpty())
            throw new UserNotFoundException(id);
        this.usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
