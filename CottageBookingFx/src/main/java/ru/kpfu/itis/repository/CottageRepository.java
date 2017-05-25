package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Cottage;

/**
 * @author Liia
 */
@Repository
public interface CottageRepository extends JpaRepository<Cottage, Long> {
}
