package com.example.demo.services;


import com.example.demo.repositories.ReparacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReparacionesService {
    @Autowired
    ReparacionesRepository reparacionesRepository;

    @Autowired
    VehiculosService vehiculosService;



}
