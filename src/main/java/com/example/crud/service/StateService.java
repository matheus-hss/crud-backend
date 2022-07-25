package com.example.crud.service;

import com.example.crud.model.State;
import com.example.crud.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public Optional<State> findById(Long id){
        return this.stateRepository.findById(id);
    }

    public List<State> findAll(){
        return this.stateRepository.findAll();
    }
}
