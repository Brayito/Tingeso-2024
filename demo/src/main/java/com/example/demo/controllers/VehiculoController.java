package com.example.demo.controllers;



import com.example.demo.entities.VehiculoEntity;
import com.example.demo.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/autofix/vehiculos")
public class VehiculoController {
    @Autowired
    VehiculoService vehiculoService;

    @GetMapping("/")
    public ResponseEntity<List<VehiculoEntity>> ListarVehiculos(){
        ArrayList<VehiculoEntity> vehiculos = vehiculoService.obtenerVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoEntity> obtenerVehiculoPorId(@PathVariable Long id) throws Exception {
        VehiculoEntity vehiculo = vehiculoService.obtenerVehiculoPorId(id);
        return ResponseEntity.ok(vehiculo);
    }

    @PostMapping("/")
    public ResponseEntity<VehiculoEntity> crearVehiculo(@RequestBody VehiculoEntity vehiculo){
        VehiculoEntity nuevoVehiculo = vehiculoService.guardarVehiculo(vehiculo);
        return ResponseEntity.ok(nuevoVehiculo);
    }

    @PutMapping("/")
    public ResponseEntity<VehiculoEntity> actualizarVehiculo(@RequestBody VehiculoEntity vehiculo){
        VehiculoEntity vehiculoActualizado = vehiculoService.actualizarVehiculo(vehiculo);
        return ResponseEntity.ok(vehiculoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarVehiculoPorId(@PathVariable Long id) throws Exception{
        var isDeleted = vehiculoService.eliminarVehiculo(id);
        return ResponseEntity.noContent().build();
    }

}
