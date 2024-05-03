package org.adaschool.api.service.user;

import org.adaschool.api.exception.UserNotFoundException;
import org.adaschool.api.repository.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMap implements UsersService {

    final HashMap<String, User> hashMap = new HashMap<>();

    @Override
    public User save(final User user) {
        hashMap.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(final String id) {
        final User user = hashMap.get(id);
        return Optional.of(user);
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(hashMap.values());
    }

    @Override
    public void deleteById(String id) {
        hashMap.remove(id);
    }

    @Override
    public User update(final User user, final String userId) {
        final User u = hashMap.get(userId);
        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        return u;
    }
}
