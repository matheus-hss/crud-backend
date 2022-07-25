package com.example.crud.service;

import com.example.crud.model.City;
import com.example.crud.model.State;
import com.example.crud.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> findByState(State state){
        return this.cityRepository.findByState(state);
    }
}
