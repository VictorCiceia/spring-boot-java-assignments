package org.adaschool.api.repository.user;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Schema(description = "Objeto que representa a un Usuario")
public class User {

    @Schema(
            description = "Identificador unico",
            type = "string",
            example = "1")
    private final String id;

    @Schema(
            description = "Fecha de creacion",
            type = "Date",
            example = "2024-05-04T22:49:38.155Z")
    private final Date createdAt;

    @Schema(
            description = "Nombre",
            type = "string",
            example = "Juan")
    private String name;

    @Schema(
            description = "Apellido",
            type = "string",
            example = "Perez")
    private String lastName;

    @Schema(
            description = "Email",
            type = "string",
            example = "usuario@empresa.com")
    private String email;

    @Hidden
    private String passwordHash;

    public User() {
        this.id = "";
        this.createdAt = new Date();
        this.name = "";
        this.lastName = "";
        this.email = "";
        this.passwordHash = "";
    }

    public User(String id, String name, String lastName, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
        this.createdAt = new Date();
    }

    public User(String id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.createdAt = new Date();
    }

    public User(UserDto userDto) {
        this.id = null;
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.passwordHash = new BCryptPasswordEncoder().encode(userDto.getPassword());
        this.createdAt = new Date();
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void update(UserDto userDto) {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        if (!userDto.getPassword().isEmpty()) {
            this.passwordHash = new BCryptPasswordEncoder().encode(userDto.getPassword());
        }
    }
}
