package com.example.demo.controllers;

import com.example.demo.entities.ReparacionEntity;
import com.example.demo.entities.ReparacionEntity;
import com.example.demo.entities.ReparacionEntity;
import com.example.demo.services.ReparacionService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReparacionController.class)
public class ReparacionControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ReparacionService reparacionService;

    @Test
    public void listarReparaciones_ShouldReturnReparations() throws Exception {
        ReparacionEntity reparacion1 = new ReparacionEntity(1L, "ABCD12",LocalDate.now(), LocalTime.now(), "Reparaciones del motor", 120000, LocalDateTime.now().minusDays(3),LocalDateTime.now());
        ReparacionEntity reparacion2 = new ReparacionEntity(2L, "EFGH34",LocalDate.now(), LocalTime.now(), "Reparaciones del Sistema de Combustible", 120000, LocalDateTime.now().minusDays(4),LocalDateTime.now());


        ArrayList<ReparacionEntity> reparaciones = new ArrayList<>(Arrays.asList(reparacion1, reparacion2));

        given(reparacionService.obtenerReparaciones()).willReturn(reparaciones);

        mockMvc.perform(get("/api/autofix/reparaciones/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].tipo_reparacion", is("Reparaciones del motor")))
                .andExpect(jsonPath("$[1].tipo_reparacion", is("Reparaciones del Sistema de Combustible")));
    }


    @Test
    public void obtenerReparacionPorId_ShouldReturnReparation() throws Exception {
        ReparacionEntity reparacion = new ReparacionEntity(1L, "ABCD12",LocalDate.now(), LocalTime.now(), "Reparaciones del motor", 120000, LocalDateTime.now().minusDays(3),LocalDateTime.now());
        given(reparacionService.obtenerReparacionPorId(1L)).willReturn(reparacion);

        mockMvc.perform(get("/api/autofix/reparaciones/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tipo_reparacion", is("Reparaciones del motor")));
    }

    @Test
    public void crearReparacion_ShouldReturnNewReparation() throws Exception {
        ReparacionEntity savedReparacion = new ReparacionEntity(3L, "LPMO56",LocalDate.now(), LocalTime.now(), "Reparaciones del motor", 120000, LocalDateTime.now().minusDays(3),LocalDateTime.now());
        given(reparacionService.guardarReparacion(Mockito.any(ReparacionEntity.class))).willReturn(savedReparacion);

        String reparacionJson = """
        {
            "num_patente": "LPMO56",
            "fecha_ingreso": "2023-10-02",
            "hora_ingreso": "10:30",
            "tipo_reparacion": "Reparaciones del motor",
            "monto_total_reparacion": 120000,
            "fecha_salida": "2023-10-05T15:30:00",
            "fecha_retiro_reparacion": "2023-10-05T17:00:00"
        }
        """;

        mockMvc.perform(post("/api/autofix/reparaciones/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reparacionJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo_reparacion", is("Reparaciones del motor")));
    }


    @Test
    public void actualizarReparacion_ShouldReturnUpdatedVehicle() throws Exception {
        ReparacionEntity updatedReparacion = new ReparacionEntity(3L, "LPMO56",LocalDate.now(), LocalTime.now(), "Reparacion y Reemplazo del Parabrisas y Cristales", 120000, LocalDateTime.now().minusDays(3),LocalDateTime.now());
        given(reparacionService.actualizarReparacion(Mockito.any(ReparacionEntity.class))).willReturn(updatedReparacion);

        String reparacionJson = """
        {
            "num_patente": "LPMO56",
            "fecha_ingreso": "2023-10-02",
            "hora_ingreso": "10:30",
            "tipo_reparacion": "Reparacion y Reemplazo del Parabrisas y Cristales",
            "monto_total_reparacion": 120000,
            "fecha_salida": "2023-10-05T15:30:00",
            "fecha_retiro_reparacion": "2023-10-05T17:00:00"
        }
        """;

        mockMvc.perform(put("/api/autofix/reparaciones/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reparacionJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo_reparacion", is("Reparacion y Reemplazo del Parabrisas y Cristales")));
    }



    @Test
    public void eliminarReparacionPorId_ShouldReturn204() throws Exception {
        Mockito.when(reparacionService.eliminarReparacion(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/autofix/reparaciones/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
