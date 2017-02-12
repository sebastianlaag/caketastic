package de.laag.service;

import de.laag.Constants;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.laag.entities.User;
import de.laag.repositories.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public User login(String login, String passwordPlain) {
        final User user = userRepository.findByLogin(login);
        final boolean pwMatches = BCrypt.checkpw(passwordPlain, user.getPasswordHash());
        if (!pwMatches) {
            throw new IllegalStateException("Incorrect password.");
        }
        return user;
    }

}
