package com.example.crud.service;

import com.example.crud.model.LabModel;
import com.example.crud.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class LabService {
    @Autowired
    private LabRepository labRepository;

    @Transactional
    public LabModel save(LabModel labModel){
        return labRepository.save(labModel);
    }

    @Transactional
    public void delete(LabModel labModel){
        labRepository.delete(labModel);
    }

    public Page<LabModel> findAll(Pageable pageable){
        return labRepository.findAll(pageable);
    }

    public Optional<LabModel> findById(UUID id){
        return labRepository.findById(id);
    }

    public Optional<LabModel> findByEmail(String email) {
        return labRepository.findByEmail(email);
    }
}
