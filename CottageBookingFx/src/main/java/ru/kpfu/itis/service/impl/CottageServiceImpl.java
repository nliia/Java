package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.Cottage;
import ru.kpfu.itis.repository.CottageRepository;
import ru.kpfu.itis.service.CottageService;

import java.util.List;

/**
 * @author Liia
 */
@Service
@Transactional
public class CottageServiceImpl implements CottageService {

    private CottageRepository cottageRepository;

    @Autowired
    CottageServiceImpl(CottageRepository cottageRepository) {
        this.cottageRepository = cottageRepository;
    }

    @Override
    public void add(Cottage cottage) {
        cottageRepository.save(cottage);
    }

    @Override
    public void delete(Long id) {
        cottageRepository.delete(cottageRepository.findOne(id));
    }

    @Override
    public void update(Cottage cottage) {
        cottageRepository.delete(cottage.getId());
        cottageRepository.save(cottage);

    }

    @Override
    public List<Cottage> getAll() {
        return cottageRepository.findAll();
    }

    @Override
    public Cottage getOne(Long id) {
        return cottageRepository.getOne(id);
    }

    @Override
    public boolean exists(Long id) {
        return cottageRepository.exists(id);
    }
}
