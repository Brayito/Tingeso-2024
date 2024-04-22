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

    public void guardarVehiculo(String num_patente, String marca, String modelo, String tipo_vehiculo, Integer ano_fabricacion, String tipo_motor, Integer num_asientos) throws Exception{
        VehiculoEntity vehiculoExistente = vehiculoRepository.findBynum_patente(num_patente);
        if (vehiculoExistente != null){
            JOptionPane.showMessageDialog(null, "El vehiculo con patente " + num_patente +"ya se encuentra registrado.", "Alerta", JOptionPane.WARNING_MESSAGE);
        }else{
            VehiculoEntity vehiculo = new VehiculoEntity();
            vehiculo.setNum_patente(num_patente);
            vehiculo.setMarca(marca);
            vehiculo.setModelo(modelo);
            vehiculo.setTipo_vehiculo(tipo_vehiculo);
            vehiculo.setAno_fabricacion(ano_fabricacion);
            vehiculo.setTipo_motor(tipo_motor);
            vehiculo.setNum_asientos(num_asientos);
            vehiculoRepository.save(vehiculo);
        }

    }

    public VehiculoEntity obtenerVehiculoPorPatente(String num_patente) throws Exception{
        VehiculoEntity vehiculo = vehiculoRepository.findBynum_patente(num_patente);
        return vehiculo;
    }

    public ArrayList<VehiculoEntity> obtenerVehiculos() throws Exception{
        return (ArrayList<VehiculoEntity>) vehiculoRepository.findAll();
    }
}
