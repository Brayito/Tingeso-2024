package com.example.demo.services;

import com.example.demo.entities.VehiculoEntity;
import com.example.demo.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;

@Service
public class VehiculoService {
    @Autowired
    VehiculoRepository vehiculoRepository;

    public VehiculoEntity guardarVehiculo(VehiculoEntity vehiculo){
        return vehiculoRepository.save(vehiculo);
    }

    public VehiculoEntity actualizarVehiculo(VehiculoEntity vehiculo){
        return vehiculoRepository.save(vehiculo);
    }

    public boolean eliminarVehiculo(Long id) throws Exception{
        try {
            vehiculoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }



    public VehiculoEntity obtenerVehiculoPorPatente(String num_patente) throws Exception{
        VehiculoEntity vehiculo = vehiculoRepository.findBynum_patente(num_patente);
        return vehiculo;
    }

    public VehiculoEntity obtenerVehiculoPorId(Long Id) throws Exception{
        return vehiculoRepository.findById(Id).get();
    }

    public ArrayList<VehiculoEntity> obtenerVehiculos(){
        return (ArrayList<VehiculoEntity>) vehiculoRepository.findAll();
    }
}
