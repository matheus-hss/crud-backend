package com.example.crud.repository;

import com.example.crud.model.LabModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LabRepository extends JpaRepository<LabModel, UUID> {
    Optional<LabModel> findByEmail(String email);
}
