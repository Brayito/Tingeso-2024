package com.example.demo.controllers;


import com.example.demo.entities.ReparacionEntity;
import com.example.demo.entities.ReparacionEntity;
import com.example.demo.services.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@CrossOrigin("*")
@RestController
@RequestMapping("/api/autofix/reparaciones")
public class ReparacionController {
    @Autowired
    ReparacionService reparacionService;

    @GetMapping("/")
    public ResponseEntity<List<ReparacionEntity>> ListarReparaciones(){
        ArrayList<ReparacionEntity> reparaciones = reparacionService.obtenerReparaciones();
        return ResponseEntity.ok(reparaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReparacionEntity> obtenerReparacionPorId(@PathVariable Long id) throws Exception {
        ReparacionEntity reparacion = reparacionService.obtenerReparacionPorId(id);
        return ResponseEntity.ok(reparacion);
    }

    @PostMapping("/")
    public ResponseEntity<ReparacionEntity> crearReparacion(@RequestBody ReparacionEntity reparacion){
        ReparacionEntity nuevoReparacion = reparacionService.guardarReparacion(reparacion);
        return ResponseEntity.ok(nuevoReparacion);
    }

    @PutMapping("/")
    public ResponseEntity<ReparacionEntity> actualizarReparacion(@RequestBody ReparacionEntity reparacion){
        ReparacionEntity reparacionActualizado = reparacionService.actualizarReparacion(reparacion);
        return ResponseEntity.ok(reparacionActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarReparacionPorId(@PathVariable Long id) throws Exception{
        var isDeleted = reparacionService.eliminarReparacion(id);
        return ResponseEntity.noContent().build();
    }
}
