package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.HighSchool;
import ru.kpfu.itis.repository.HighSchoolRepository;
import ru.kpfu.itis.service.HighSchoolService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Liia
 */
@Service
@Transactional
public class HighSchoolServiceImpl implements HighSchoolService {

    private HighSchoolRepository highSchoolRepository;

    @Autowired
    public HighSchoolServiceImpl(HighSchoolRepository highSchoolRepository) {
        this.highSchoolRepository = highSchoolRepository;
    }


    public Set<String> getAllCities() {
        Set<String> cities = new HashSet<String>();
        List<HighSchool> highSchools = highSchoolRepository.findAll();
        for (int i = 0; i < highSchools.size(); i++) {
            cities.add(highSchools.get(i).getCity());
        }
        return cities;
    }

    public List<HighSchool> getByCity(String city) {
        return highSchoolRepository.findByCity(city);
    }

    @Override
    public void add(HighSchool highSchool) {
        highSchoolRepository.save(highSchool);
    }

    @Override
    public void delete(Long id) {
        highSchoolRepository.delete(id);
    }

    @Override
    public List<HighSchool> getALl() {
        return highSchoolRepository.findAll();
    }

    @Override
    public HighSchool getOne(Long id) {
        return highSchoolRepository.getOne(id);
    }
}
