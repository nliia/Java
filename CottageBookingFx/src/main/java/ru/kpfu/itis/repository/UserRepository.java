package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.User;

/**
 * @author Liia
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLogin(String login);
}
