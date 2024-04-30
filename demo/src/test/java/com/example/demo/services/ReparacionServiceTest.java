package com.example.demo.services;

import com.example.demo.entities.ReparacionEntity;
import com.example.demo.repositories.ReparacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReparacionServiceTest {

    @Mock
    private ReparacionRepository reparacionRepository;

    @InjectMocks
    private ReparacionService reparacionService;

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




}
