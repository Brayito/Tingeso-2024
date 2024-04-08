package com.example.demo.repositories;

import com.example.demo.entities.ReparacionesEntity;
import com.example.demo.entities.VehiculosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehiculosRepository extends JpaRepository<ReparacionesEntity, Long> {
    @Query("select e from VehiculosEntity e where e.num_patente = :rut")
    VehiculosEntity findBynum_patente(@Param("num_patente")String num_patente);
}
