package ru.kpfu.itis.liia_nurullina.dao;

import ru.kpfu.itis.liia_nurullina.model.User;

import java.util.List;

public interface UsersDao {

    void add(User user);

    void update(User user);

    List findAll();

    User findByPrimaryKey(Long id);

    User findByLogin(String login);
}
