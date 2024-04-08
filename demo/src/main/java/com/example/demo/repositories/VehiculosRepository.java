package com.example.demo.repositories;

import com.example.demo.entities.VehiculosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculosRepository extends JpaRepository<VehiculosEntity, Long> {
    @Query("select e from VehiculosEntity e where e.num_patente = :num_patente")
    VehiculosEntity findBynum_patente(@Param("num_patente")String num_patente);
}
