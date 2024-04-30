package com.example.demo.repositories;


import com.example.demo.entities.ReparacionEntity;
import com.example.demo.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparacionRepository extends JpaRepository<ReparacionEntity, Long> {

    @Query("select e from ReparacionEntity e where e.num_patente = :num_patente")
    ReparacionEntity findBynum_patente(@Param("num_patente")String num_patente);

}
