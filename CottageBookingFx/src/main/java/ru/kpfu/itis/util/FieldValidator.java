package ru.kpfu.itis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.repository.UserRepository;

/**
 * @author Liia
 */
@Component
public class FieldValidator {
    private static final int EMAIL_SIZE = 5;
    private static final int PASSWORD_SIZE = 6;

    @Autowired
    private UserRepository userRepository;

    public String validateUser(String email, String password) {
        if (password.length() < PASSWORD_SIZE)
            return "Password must be min 6 symbols";

        if (email.length() < EMAIL_SIZE) {
            return "Email must be at least 5 symbols";
        }


        if (userRepository.findOneByLogin(email) != null) {
            return "User with this login exists";
        }
        return "true";
    }
}
