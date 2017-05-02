package ru.kpfu.itis.service;

import ru.kpfu.itis.model.User;

/**
 * @author Liia
 */
public interface UserService {
    User getByEmail(String email);

    void add(User user);
}
