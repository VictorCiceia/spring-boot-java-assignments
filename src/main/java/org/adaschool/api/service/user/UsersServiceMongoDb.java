package org.adaschool.api.service.user;

import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMongoDb implements UsersService {

    private final UserMongoRepository userMongoRepository;

    @Autowired
    public UsersServiceMongoDb(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public User save(User user) {
        return this.userMongoRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return this.userMongoRepository.findById(id);
    }

    @Override
    public List<User> all() {
        return this.userMongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        this.userMongoRepository.deleteById(id);
    }

    @Override
    public User update(User user, String userId) {
        final User u = this.userMongoRepository.findById(userId).get();
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setLastName(user.getLastName());
        return this.save(u);
    }
}
