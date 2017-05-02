package ru.kpfu.itis.service;

import ru.kpfu.itis.model.HighSchool;

import java.util.List;
import java.util.Set;

public interface HighSchoolService {

    Set<String> getAllCities();

   List<HighSchool> getByCity(String city);

    void add(HighSchool highSchool);

    void delete(Long id);

    List<HighSchool> getALl();

    HighSchool getOne(Long id);
}
