package ru.kpfu.itis.service;

import ru.kpfu.itis.model.Cottage;

import java.util.List;

/**
 * @author Liia
 */

public interface CottageService {

    void add(Cottage cottage);

    void update(Cottage cottage);

    void delete(Long id);

    List<Cottage> getAll();

    Cottage getOne(Long id);

    boolean exists(Long id);
}
