package com.example.demo.services;

import com.example.demo.entities.VehiculoEntity;
import com.example.demo.repositories.VehiculoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class VehiculoServiceTest {


    @Mock
    private VehiculoRepository vehiculoRepository;

    @InjectMocks
    private VehiculoService vehiculoService;
    private VehiculoEntity vehiculo;




    @Test
    void guardarVehiculo_ShouldSaveVehiculo() {
        VehiculoEntity vehiculo = new VehiculoEntity();
        when(vehiculoRepository.save(any(VehiculoEntity.class))).thenReturn(vehiculo);

        VehiculoEntity savedVehiculo = vehiculoService.guardarVehiculo(vehiculo);
        assertThat(savedVehiculo).isNotNull();
        assertThat(savedVehiculo).isEqualTo(vehiculo);
        verify(vehiculoRepository).save(vehiculo);
    }

    @Test
    void actualizarVehiculo_ShouldUpdateVehiculo() {
        VehiculoEntity vehiculo = new VehiculoEntity();
        when(vehiculoRepository.save(any(VehiculoEntity.class))).thenReturn(vehiculo);

        VehiculoEntity updatedVehiculo = vehiculoService.actualizarVehiculo(vehiculo);
        assertThat(updatedVehiculo).isNotNull();
        assertThat(updatedVehiculo).isEqualTo(vehiculo);
        verify(vehiculoRepository).save(vehiculo);
    }

    @Test
    void eliminarVehiculo_ShouldDeleteVehiculo() throws Exception {
        Long vehiculoId = 1L;
        doNothing().when(vehiculoRepository).deleteById(vehiculoId);

        boolean isDeleted = vehiculoService.eliminarVehiculo(vehiculoId);
        assertThat(isDeleted).isTrue();
        verify(vehiculoRepository).deleteById(vehiculoId);
    }

    @Test
    void obtenerVehiculosPorPatente_ShouldReturnVehiculo() throws Exception {
        String numPatente = "ABCD12";
        VehiculoEntity vehiculo = new VehiculoEntity();
        when(vehiculoRepository.findBynum_patente(numPatente)).thenReturn(vehiculo);

        VehiculoEntity foundVehiculo = vehiculoService.obtenerVehiculoPorPatente(numPatente);
        assertThat(foundVehiculo).isNotNull();
        assertThat(foundVehiculo).isEqualTo(vehiculo);
        verify(vehiculoRepository).findBynum_patente(numPatente);
    }

    @Test
    void obtenerVehiculoPorId_ShouldReturnVehiculo() throws Exception {
        Long id = 1L;
        VehiculoEntity vehiculo = new VehiculoEntity();
        when(vehiculoRepository.findById(id)).thenReturn(Optional.of(vehiculo));

        VehiculoEntity foundVehiculo = vehiculoService.obtenerVehiculoPorId(id);
        assertThat(foundVehiculo).isNotNull();
        assertThat(foundVehiculo).isEqualTo(vehiculo);
        verify(vehiculoRepository).findById(id);
    }

    @Test
    void obtenerVehiculos_ShouldReturnAllVehicles(){
        VehiculoEntity vehiculo1 = new VehiculoEntity();
        vehiculo1.setId(1L);

        VehiculoEntity vehiculo2 = new VehiculoEntity();
        vehiculo2.setId(2L);

        ArrayList<VehiculoEntity> vehiculos = new ArrayList<>();
        vehiculos.add(vehiculo1);
        vehiculos.add(vehiculo2);

        when(vehiculoRepository.findAll()).thenReturn(vehiculos);

        ArrayList<VehiculoEntity> lista = vehiculoService.obtenerVehiculos();

        assertThat(lista).isNotNull();
        assertThat(lista).hasSize(2);
        assertThat(lista).containsExactlyInAnyOrder(vehiculo1,vehiculo2);
        verify(vehiculoRepository).findAll();

    }

}
