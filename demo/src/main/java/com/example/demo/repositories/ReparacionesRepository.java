package com.example.demo.repositories;


import com.example.demo.entities.ReparacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparacionesRepository extends JpaRepository<ReparacionesEntity, Long> {

}
