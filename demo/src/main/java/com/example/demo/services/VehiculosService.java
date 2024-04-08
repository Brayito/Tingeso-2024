package com.example.demo.services;

import com.example.demo.repositories.VehiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculosService {
    @Autowired
    VehiculosRepository vehiculosRepository;

}
