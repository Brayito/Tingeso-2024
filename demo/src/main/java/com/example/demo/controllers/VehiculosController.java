package com.example.demo.controllers;



import com.example.demo.services.VehiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class VehiculosController {
    @Autowired
    VehiculosService vehiculosService;

    @GetMapping("/mensaje")
    public ResponseEntity<String> obtenerMensaje(){
        return ResponseEntity.ok("Saludos desde backend");
    }

}