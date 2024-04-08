package com.example.demo.services;

import com.example.demo.repositories.VehiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class VehiculosService {
    @Autowired
    VehiculosRepository vehiculosRepository;

    @Autowired
    VehiculosService vehiculosService;
}
