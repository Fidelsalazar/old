package com.example.apistock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.example.apistock.models.entities.Center;
import java.util.UUID;

@Repository
public interface CenterRepository extends JpaRepository<Center, UUID> {

    Center findByCenter( String name);
}
