package com.example.demo.services;

import com.example.demo.entities.VehiculosEntity;
import com.example.demo.repositories.VehiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;

@Service
public class VehiculosService {
    @Autowired
    VehiculosRepository vehiculosRepository;

    public void guardarVehiculo(String num_patente, String marca, String modelo, String tipo_vehiculo, Integer ano_fabricacion, String tipo_motor, Integer num_asientos) throws Exception{
        VehiculosEntity vehiculoExistente = vehiculosRepository.findBynum_patente(num_patente);
        if (vehiculoExistente != null){
            JOptionPane.showMessageDialog(null, "El vehiculo con patente " + num_patente +"ya se encuentra registrado.", "Alerta", JOptionPane.WARNING_MESSAGE);
        }else{
            VehiculosEntity vehiculo = new VehiculosEntity();
            vehiculo.setNum_patente(num_patente);
            vehiculo.setMarca(marca);
            vehiculo.setNum_patente(modelo);
            vehiculo.setTipo_vehiculo(tipo_vehiculo);
            vehiculo.setAno_fabricacion(ano_fabricacion);
            vehiculo.setTipo_motor(tipo_motor);
            vehiculo.setNum_asientos(num_asientos);
        }
    }

    public VehiculosEntity obtenerVehiculoPorPatente(String num_patente){
        VehiculosEntity vehiculo = vehiculosRepository.findBynum_patente(num_patente);
        return vehiculo;
    }

    public ArrayList<VehiculosEntity> obtenerVehiculos(){
        return (ArrayList<VehiculosEntity>) vehiculosRepository.findAll();
    }
}
