package net.java.springboot.assessment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.java.springboot.assessment.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    
    List<City> findByName(String name);
    
}
