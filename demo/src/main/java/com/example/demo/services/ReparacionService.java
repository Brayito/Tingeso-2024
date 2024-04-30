package com.example.demo.services;


import com.example.demo.entities.ReparacionEntity;
import com.example.demo.entities.VehiculoEntity;
import com.example.demo.repositories.ReparacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Service
public class ReparacionService {
    @Autowired
    ReparacionRepository reparacionRepository;

    @Autowired
    VehiculoService vehiculoService;



    public ReparacionEntity guardarReparacion(ReparacionEntity reparacion){
        return reparacionRepository.save(reparacion);
    }

    public ReparacionEntity actualizarReparacion(ReparacionEntity reparacion){
        return reparacionRepository.save(reparacion);
    }

    public boolean eliminarReparacion(Long id) throws Exception{
        try {
            reparacionRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    public ReparacionEntity obtenerReparacionesPorPatente(String num_patente) throws Exception{
        ReparacionEntity reparacion = reparacionRepository.findBynum_patente(num_patente);
        return reparacion;
    }

    public ReparacionEntity obtenerReparacionPorId(Long Id) throws Exception{
        return reparacionRepository.findById(Id).get();
    }

    public ArrayList<ReparacionEntity> obtenerReparaciones(){
        return (ArrayList<ReparacionEntity>) reparacionRepository.findAll();
    }


    private double calcularDescuentos(double monto_base, LocalDate fechaIngreso, LocalTime hora_ingreso, Integer num_reparaciones, String tipo_motor) {

        double descuentoTotal = 0;

        // (Revisar si se pued usar el num de reparaciones sin definir en entidad vehiculo, sino buscando todas las reparaciones de una patente especifica)
        // Descuento por num de reparaciones
        switch (tipo_motor) {
            case "Gasolina" -> {
                if (num_reparaciones == 1 || num_reparaciones == 2) {
                    descuentoTotal = descuentoTotal + monto_base * 0.05;
                } else if (num_reparaciones >= 3 && num_reparaciones <= 5) {
                    descuentoTotal = descuentoTotal + monto_base * 0.10;
                } else if (num_reparaciones >= 6 && num_reparaciones <= 9) {
                    descuentoTotal = descuentoTotal + monto_base * 0.15;
                } else {
                    descuentoTotal = descuentoTotal + monto_base * 0.20;
                }
            }
            case "Diésel" -> {
                if (num_reparaciones == 1 || num_reparaciones == 2) {
                    descuentoTotal = descuentoTotal + monto_base * 0.07;
                } else if (num_reparaciones >= 3 && num_reparaciones <= 5) {
                    descuentoTotal = descuentoTotal + monto_base * 0.12;
                } else if (num_reparaciones >= 6 && num_reparaciones <= 9) {
                    descuentoTotal = descuentoTotal + monto_base * 0.17;
                } else {
                    descuentoTotal = descuentoTotal + monto_base * 0.22;
                }
            }
            case "Híbrido" -> {
                if (num_reparaciones == 1 || num_reparaciones == 2) {
                    descuentoTotal = descuentoTotal + monto_base * 0.10;
                } else if (num_reparaciones >= 3 && num_reparaciones <= 5) {
                    descuentoTotal = descuentoTotal + monto_base * 0.15;
                } else if (num_reparaciones >= 6 && num_reparaciones <= 9) {
                    descuentoTotal = descuentoTotal + monto_base * 0.20;
                } else {
                    descuentoTotal = descuentoTotal + monto_base * 0.25;
                }
            }
            case "Eléctrico" -> {
                if (num_reparaciones == 1 || num_reparaciones == 2) {
                    descuentoTotal = descuentoTotal + monto_base * 0.08;
                } else if (num_reparaciones >= 3 && num_reparaciones <= 5) {
                    descuentoTotal = descuentoTotal + monto_base * 0.13;
                } else if (num_reparaciones >= 6 && num_reparaciones <= 9) {
                    descuentoTotal = descuentoTotal + monto_base * 0.18;
                } else {
                    descuentoTotal = descuentoTotal + monto_base * 0.23;
                }
            }
        }

        // ------------------------------

        //Descuento por dia de atencion
        LocalTime horaInicioDescuento = LocalTime.of(9,0);
        LocalTime horaFinDescuento = LocalTime.of(12,0);

        if (!hora_ingreso.isBefore(horaInicioDescuento) && !hora_ingreso.isAfter(horaFinDescuento)) {
            descuentoTotal =  descuentoTotal + monto_base * 0.10;
        }
        // -------------------------------

        //Descuento por bonos
        // -------------------------------

        return descuentoTotal;
    }


    private double calcularRecargos(double monto_base, Integer kilometraje, Integer ano_fabricacion, String tipo_vehiculo, LocalDateTime fecha_salida, LocalDateTime fecha_retiro_vehiculo) {

        double recargoTotal = 0;

        // Recargo por kilometraje y antigüedad

        LocalDate fecha_fabricacion = LocalDate.of(ano_fabricacion,1,1);
        LocalDate fecha_actual = LocalDate.now();
        Period periodo_antiguedad = Period.between(fecha_fabricacion,fecha_actual);

        int antiguedad = periodo_antiguedad.getYears();

        switch (tipo_vehiculo) {
            case "Sedán", "Hatchback" -> {
                // Antigüedad
                if (antiguedad >= 0 && antiguedad <= 5) {
                    recargoTotal = recargoTotal + monto_base * 0.00;
                }if (antiguedad >= 6 && antiguedad <= 10) {
                    recargoTotal = recargoTotal + monto_base * 0.05;
                }if (antiguedad >= 11 && antiguedad <= 15) {
                    recargoTotal = recargoTotal + monto_base * 0.09;
                }if (antiguedad >= 16){
                    recargoTotal = recargoTotal + monto_base * 0.15;
                }

                //Kilometraje
                if (kilometraje >= 0 && kilometraje <= 5000) {
                    recargoTotal = recargoTotal + monto_base * 0.00;
                }if (kilometraje >= 5001 && kilometraje <= 12000) {
                    recargoTotal = recargoTotal + monto_base * 0.03;
                }if (kilometraje >= 12001 && kilometraje <= 25000) {
                    recargoTotal = recargoTotal + monto_base * 0.07;
                }if (kilometraje >= 25001 && kilometraje <= 40000){
                    recargoTotal = recargoTotal + monto_base * 0.12;
                }if (kilometraje >= 40001){
                    recargoTotal = recargoTotal + monto_base * 0.20;
                }
            }
            case "SUV", "Pickup", "Furgoneta" -> {
                // Antigüedad
                if (antiguedad >= 0 && antiguedad <= 5) {
                    recargoTotal = recargoTotal + monto_base * 0.00;
                }if (antiguedad >= 6 && antiguedad <= 10) {
                    recargoTotal = recargoTotal + monto_base * 0.07;
                }if (antiguedad >= 11 && antiguedad <= 15) {
                    recargoTotal = recargoTotal + monto_base * 0.11;
                }if (antiguedad >= 16){
                    recargoTotal = recargoTotal + monto_base * 0.20;
                }

                //Kilometraje
                if (kilometraje >= 0 && kilometraje <= 5000) {
                    recargoTotal = recargoTotal + monto_base * 0.00;
                }if (kilometraje >= 5001 && kilometraje <= 12000) {
                    recargoTotal = recargoTotal + monto_base * 0.05;
                }if (kilometraje >= 12001 && kilometraje <= 25000) {
                    recargoTotal = recargoTotal + monto_base * 0.09;
                }if (kilometraje >= 25001 && kilometraje <= 40000){
                    recargoTotal = recargoTotal + monto_base * 0.12;
                }if (kilometraje >= 40000){
                    recargoTotal = recargoTotal + monto_base * 0.20;
                }
            }
        }
        // -------------------------------------------------



        // Recargo por retraso del retiro

        long horas_retraso = ChronoUnit.HOURS.between(fecha_salida,fecha_retiro_vehiculo);

        if (horas_retraso > 24){
            long dias_retraso = horas_retraso / 24;
            recargoTotal = recargoTotal + (dias_retraso * 0.05);
        }
        // -------------------------------------------------

        return recargoTotal;
    }

    /*
    Costo total reparación = = [Suma(Reparaciones) + Recargos – Descuentos] + IVA


    ////////    Descuentos      ///////////
    Descuento por num de reparaciones

    Descuento por día de atención (10% sobre el monto total de reparación dias lunes y jueves entre 09:00 y 12:00)

    Decuento por bono (Toyota, Ford, Hyundai, Honda)
     Llega por correo
            - Toyota: 5 bonos de 70.000 pesos
            - Ford: 2 bonos de 50.000 pesos
            - Hyundai: 1 bono de 30.000 pesos
            - Honda: 7 bonos de 40.000 pesos}

     La empresa decide a qué auto dárselo



     /////////      Recargos            /////////////////

     Recargo por kilometraje (según kilometraje y tipo_vehiculo)

     Recargo por antiguedad sobre el costo total

     Recargo por retraso en retirar el vehiculo = 5% sobre el monto total de reparación por cada día de atraso contando la fecha donde está listo
     */



}
