package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "reparaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReparacionesEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id;
    private Date fecha_ingreso;
    private Date hora_ingreso;
    private String tipo_reparacion;
    private Integer monto_total_reparacion;
    private Date fecha_salida;
    private Date hora_salida;
    private Date fecha_retiro_vehiculo;
    private Date hora_retiro_vehiculo;
}
