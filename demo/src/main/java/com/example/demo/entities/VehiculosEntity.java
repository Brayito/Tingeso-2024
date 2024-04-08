package com.example.demo.entities;


import jakarta.persistence.*;

@Entity
public class VehiculosEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id;
    private String num_patente;
    private String marca;
    private String modelo;
    private String tipo_vehiculo;
    private Integer ano_fabricacion;
    private String tipo_motor;
    private Integer num_asientos;
}
