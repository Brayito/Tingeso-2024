package com.example.demo.services;

import com.example.demo.entities.ReparacionEntity;
import com.example.demo.entities.VehiculoEntity;
import com.example.demo.repositories.ReparacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReparacionServiceTest {

    @Mock
    private ReparacionRepository reparacionRepository;
    @Mock
    private VehiculoService vehiculoService;

    @InjectMocks
    private ReparacionService reparacionService;
    private ReparacionEntity reparacion;
    private VehiculoEntity vehiculo;






    @Test
    void guardarReparacion_ShouldSaveReparacion() {
        ReparacionEntity reparacion = new ReparacionEntity();
        when(reparacionRepository.save(any(ReparacionEntity.class))).thenReturn(reparacion);

        ReparacionEntity savedReparacion = reparacionService.guardarReparacion(reparacion);
        assertThat(savedReparacion).isNotNull();
        assertThat(savedReparacion).isEqualTo(reparacion);
        verify(reparacionRepository).save(reparacion);
    }

    @Test
    void actualizarReparacion_ShouldUpdateReparacion() {
        ReparacionEntity reparacion = new ReparacionEntity();
        when(reparacionRepository.save(any(ReparacionEntity.class))).thenReturn(reparacion);

        ReparacionEntity updatedReparacion = reparacionService.actualizarReparacion(reparacion);
        assertThat(updatedReparacion).isNotNull();
        assertThat(updatedReparacion).isEqualTo(reparacion);
        verify(reparacionRepository).save(reparacion);
    }

    @Test
    void eliminarReparacion_ShouldDeleteReparacion() throws Exception {
        Long reparacionId = 1L;
        doNothing().when(reparacionRepository).deleteById(reparacionId);

        boolean isDeleted = reparacionService.eliminarReparacion(reparacionId);
        assertThat(isDeleted).isTrue();
        verify(reparacionRepository).deleteById(reparacionId);
    }

    @Test
    void obtenerReparacionesPorPatente_ShouldReturnReparacion() throws Exception {
        String numPatente = "ABCD12";
        ReparacionEntity reparacion = new ReparacionEntity();
        when(reparacionRepository.findBynum_patente(numPatente)).thenReturn(reparacion);

        ReparacionEntity foundReparacion = reparacionService.obtenerReparacionesPorPatente(numPatente);
        assertThat(foundReparacion).isNotNull();
        assertThat(foundReparacion).isEqualTo(reparacion);
        verify(reparacionRepository).findBynum_patente(numPatente);
    }

    @Test
    void obtenerReparacionPorId_ShouldReturnReparacion() throws Exception {
        Long id = 1L;
        ReparacionEntity reparacion = new ReparacionEntity();
        when(reparacionRepository.findById(id)).thenReturn(Optional.of(reparacion));

        ReparacionEntity foundReparacion = reparacionService.obtenerReparacionPorId(id);
        assertThat(foundReparacion).isNotNull();
        assertThat(foundReparacion).isEqualTo(reparacion);
        verify(reparacionRepository).findById(id);
    }

    @Test
    void calcularRecargos_ShouldCalculateIncreaseKm1Year1Sedan() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("PLKT23");
        vehiculo.setTipo_vehiculo("Sedan");
        vehiculo.setAno_fabricacion(2023);
        vehiculo.setKilometraje(1000);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("PLKT23");
        reparacion.setMonto_total_reparacion(20000);
        reparacion.setFecha_salida(LocalDateTime.now().minusDays(2));
        reparacion.setFecha_retiro_vehiculo(LocalDateTime.now());

        when(vehiculoService.obtenerVehiculoPorPatente("PLKT23")).thenReturn(vehiculo);


        double recargos = reparacionService.calcularRecargos(reparacion);
        double recargoEsperado = 20000 * 0.00 + 20000 * 0.00 + 20000 * 2 * 0.05;
        assertThat(recargos).isEqualTo(recargoEsperado);
    }

    @Test
    void calcularRecargos_ShouldCalculateIncreaseKm2Year2Sedan() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("PLKT23");
        vehiculo.setTipo_vehiculo("Sedan");
        vehiculo.setAno_fabricacion(2016);
        vehiculo.setKilometraje(6000);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("PLKT23");
        reparacion.setMonto_total_reparacion(20000);
        reparacion.setFecha_salida(LocalDateTime.now().minusDays(2));
        reparacion.setFecha_retiro_vehiculo(LocalDateTime.now());

        when(vehiculoService.obtenerVehiculoPorPatente("PLKT23")).thenReturn(vehiculo);


        double recargos = reparacionService.calcularRecargos(reparacion);
        double recargoEsperado = 20000 * 0.05 + 20000 * 0.03 + 20000 * 2 * 0.05;
        assertThat(recargos).isEqualTo(recargoEsperado);
    }

    @Test
    void calcularRecargos_ShouldCalculateIncreaseKm3Year3Sedan() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("PLKT23");
        vehiculo.setTipo_vehiculo("Sedan");
        vehiculo.setAno_fabricacion(2010);
        vehiculo.setKilometraje(15000);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("PLKT23");
        reparacion.setMonto_total_reparacion(20000);
        reparacion.setFecha_salida(LocalDateTime.now().minusDays(2));
        reparacion.setFecha_retiro_vehiculo(LocalDateTime.now());

        when(vehiculoService.obtenerVehiculoPorPatente("PLKT23")).thenReturn(vehiculo);


        double recargos = reparacionService.calcularRecargos(reparacion);
        double recargoEsperado = 20000 * 0.09 + 20000 * 0.07 + 20000 * 2 * 0.05;
        assertThat(recargos).isEqualTo(recargoEsperado);
    }

    @Test
    void calcularRecargos_ShouldCalculateIncreaseKm4Year4Hatchback() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("PLKT23");
        vehiculo.setTipo_vehiculo("Hatchback");
        vehiculo.setAno_fabricacion(1998);
        vehiculo.setKilometraje(30000);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("PLKT23");
        reparacion.setMonto_total_reparacion(20000);
        reparacion.setFecha_salida(LocalDateTime.now().minusDays(2));
        reparacion.setFecha_retiro_vehiculo(LocalDateTime.now());

        when(vehiculoService.obtenerVehiculoPorPatente("PLKT23")).thenReturn(vehiculo);


        double recargos = reparacionService.calcularRecargos(reparacion);
        double recargoEsperado = 20000 * 0.15 + 20000 * 0.12 + 20000 * 2 * 0.05;
        assertThat(recargos).isEqualTo(recargoEsperado);
    }


    @Test
    void calcularRecargos_ShouldCalculateIncreaseKmYearSUV1() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("PLKT23");
        vehiculo.setTipo_vehiculo("SUV");
        vehiculo.setAno_fabricacion(2023);
        vehiculo.setKilometraje(0);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("PLKT23");
        reparacion.setMonto_total_reparacion(20000);
        reparacion.setFecha_salida(LocalDateTime.now().minusDays(1));
        reparacion.setFecha_retiro_vehiculo(LocalDateTime.now());

        when(vehiculoService.obtenerVehiculoPorPatente("PLKT23")).thenReturn(vehiculo);


        double recargos = reparacionService.calcularRecargos(reparacion);
        double recargoEsperado = 20000 * 0.00 + 20000 * 0.00;
        assertThat(recargos).isEqualTo(recargoEsperado);
    }
    @Test
    void calcularRecargos_ShouldCalculateIncreaseKmYearSUV3() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("PLKT23");
        vehiculo.setTipo_vehiculo("SUV");
        vehiculo.setAno_fabricacion(2010);
        vehiculo.setKilometraje(15000);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("PLKT23");
        reparacion.setMonto_total_reparacion(20000);
        reparacion.setFecha_salida(LocalDateTime.now().minusDays(1));
        reparacion.setFecha_retiro_vehiculo(LocalDateTime.now());

        when(vehiculoService.obtenerVehiculoPorPatente("PLKT23")).thenReturn(vehiculo);


        double recargos = reparacionService.calcularRecargos(reparacion);
        double recargoEsperado = 20000 * 0.11 + 20000 * 0.09;
        assertThat(recargos).isEqualTo(recargoEsperado);
    }


    @Test
    void calcularDescuentos_ShouldApplyDiscountGas1() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Gasolina");
        vehiculo.setNum_reparaciones(1);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.05;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountGas2() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Gasolina");
        vehiculo.setNum_reparaciones(4);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.10;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountGas3() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Gasolina");
        vehiculo.setNum_reparaciones(8);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.15;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountGas4() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Gasolina");
        vehiculo.setNum_reparaciones(10);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.20;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }



    @Test
    void calcularDescuentos_ShouldApplyDiscountDie1() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Diesel");
        vehiculo.setNum_reparaciones(1);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.07;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }
    @Test
    void calcularDescuentos_ShouldApplyDiscountDie2() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Diesel");
        vehiculo.setNum_reparaciones(4);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.12;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }
    @Test
    void calcularDescuentos_ShouldApplyDiscountDie3() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Diesel");
        vehiculo.setNum_reparaciones(7);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.17;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }
    @Test
    void calcularDescuentos_ShouldApplyDiscountDie4() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Diesel");
        vehiculo.setNum_reparaciones(12);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.22;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountEle1() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Electrico");
        vehiculo.setNum_reparaciones(1);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.08;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountEle2() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Electrico");
        vehiculo.setNum_reparaciones(3);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.13;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountEle3() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Electrico");
        vehiculo.setNum_reparaciones(6);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.18;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountEle4() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Electrico");
        vehiculo.setNum_reparaciones(10);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.23;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountHib1() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Hibrido");
        vehiculo.setNum_reparaciones(1);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.10;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountHib2() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Hibrido");
        vehiculo.setNum_reparaciones(4);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.15;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountHib3() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Hibrido");
        vehiculo.setNum_reparaciones(7);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.20;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void calcularDescuentos_ShouldApplyDiscountHib4() throws Exception {

        vehiculo = new VehiculoEntity();
        vehiculo.setNum_patente("LMNO11");
        vehiculo.setTipo_motor("Hibrido");
        vehiculo.setNum_reparaciones(10);

        reparacion = new ReparacionEntity();
        reparacion.setNum_patente("LMNO11");
        reparacion.setMonto_total_reparacion(10000);
        reparacion.setHora_ingreso(LocalTime.of(10, 0));
        when(vehiculoService.obtenerVehiculoPorPatente("LMNO11")).thenReturn(vehiculo);

        double descuentoEsperado = 10000 * 0.25;

        double descuento = reparacionService.calcularDescuentos(reparacion);

        assertThat(descuento).isEqualTo(descuentoEsperado);
    }

    @Test
    void obtenerReparaciones_ShouldReturnAllVehicles(){
        ReparacionEntity reparacion1 = new ReparacionEntity();
        reparacion1.setId(1L);

        ReparacionEntity reparacion2 = new ReparacionEntity();
        reparacion2.setId(2L);

        ArrayList<ReparacionEntity> reparaciones = new ArrayList<>();
        reparaciones.add(reparacion1);
        reparaciones.add(reparacion2);

        when(reparacionRepository.findAll()).thenReturn(reparaciones);

        ArrayList<ReparacionEntity> lista = reparacionService.obtenerReparaciones();

        assertThat(lista).isNotNull();
        assertThat(lista).hasSize(2);
        assertThat(lista).containsExactlyInAnyOrder(reparacion1,reparacion2);
        verify(reparacionRepository).findAll();

    }






}
