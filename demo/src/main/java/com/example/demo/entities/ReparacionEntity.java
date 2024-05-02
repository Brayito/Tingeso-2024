package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "reparaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReparacionEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id;

    @Column(name = "num_patente")
    private String num_patente;

    private LocalDate fecha_ingreso;
    private LocalTime hora_ingreso;
    private String tipo_reparacion;
    private double monto_total_reparacion;
    private LocalDateTime fecha_salida;
    private LocalDateTime fecha_retiro_vehiculo;
}
