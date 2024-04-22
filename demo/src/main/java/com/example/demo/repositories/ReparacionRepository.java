package com.example.demo.repositories;


import com.example.demo.entities.ReparacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparacionRepository extends JpaRepository<ReparacionEntity, Long> {

}
