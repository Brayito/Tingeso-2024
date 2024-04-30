package com.example.demo.controllers;

import com.example.demo.entities.VehiculoEntity;
import com.example.demo.services.VehiculoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehiculoController.class)
public class VehiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehiculoService vehiculoService;

    @Test
    public void listarVehiculos_ShouldReturnVehicles() throws Exception {
        VehiculoEntity vehiculo1 = new VehiculoEntity(1L, "XR5141", "Toyota", "Corolla", "Pickup", 2020, "Gasolina", 4, 5000, 1);
        VehiculoEntity vehiculo2 = new VehiculoEntity(2L, "DLJG89", "Honda", "Civic","Sedan", 2019, "Gasolina", 4, 10000, 2);

        ArrayList<VehiculoEntity> vehiculos = new ArrayList<>(Arrays.asList(vehiculo1, vehiculo2));

        given(vehiculoService.obtenerVehiculos()).willReturn(vehiculos);

        mockMvc.perform(get("/api/autofix/vehiculos/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].marca", is("Toyota")))
                .andExpect(jsonPath("$[1].marca", is("Honda")));
    }


    @Test
    public void obtenerVehiculoPorId_ShouldReturnVehicle() throws Exception {
        VehiculoEntity vehiculo = new VehiculoEntity(1L, "XR5141", "Toyota", "Corolla", "Pickup", 2020, "Gasolina", 4, 5000, 1);
        given(vehiculoService.obtenerVehiculoPorId(1L)).willReturn(vehiculo);

        mockMvc.perform(get("/api/autofix/vehiculos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.marca", is("Toyota")));
    }


    @Test
    public void crearVehiculo_ShouldReturnNewVehicle() throws Exception {
        //VehiculoEntity newVehiculo = new VehiculoEntity(null, "ABCD12", "Ford", "Fiesta", "Sedan", 2021, "Electrico", 5, 0, 0);
        VehiculoEntity savedVehiculo = new VehiculoEntity(3L, "ABCD12", "Ford", "Fiesta", "Sedan", 2021, "Electrico", 5, 0, 0);
        given(vehiculoService.guardarVehiculo(Mockito.any(VehiculoEntity.class))).willReturn(savedVehiculo);

        String vehiculoJson = """
        {
            "num_patente": "ABCD12",
            "marca": "Ford",
            "modelo": "Fiesta",
            "tipo_vehiculo": "Sedan",
            "ano_fabricacion": 2021,
            "tipo_motor": "Electrico",
            "num_asientos": 5,
            "kilometraje": 0,
            "num_reparaciones": 0
        }
        """;

        mockMvc.perform(post("/api/autofix/vehiculos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehiculoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca", is("Ford")));
    }

    @Test
    public void actualizarVehiculo_ShouldReturnUpdatedVehicle() throws Exception {
        VehiculoEntity updatedVehiculo = new VehiculoEntity(1L, "XR5141", "Toyota", "Corolla", "Pickup", 2021, "Gasolina", 4, 7000, 2);
        given(vehiculoService.actualizarVehiculo(Mockito.any(VehiculoEntity.class))).willReturn(updatedVehiculo);

        String vehiculoJson = """
        {
            "id": 1,
            "num_patente": "XR5141",
            "marca": "Toyota",
            "modelo": "Corolla",
            "tipo_vehiculo": "Pickup",
            "ano_fabricacion": 2021,
            "tipo_motor": "Gasolina",
            "num_asientos": 4,
            "kilometraje": 7000,
            "num_reparaciones": 2
        }
        """;

        mockMvc.perform(put("/api/autofix/vehiculos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehiculoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca", is("Toyota")));
    }


    @Test
    public void eliminarVehiculoPorId_ShouldReturn204() throws Exception {
        Mockito.when(vehiculoService.eliminarVehiculo(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/autofix/vehiculos/{id}", 1L))
                .andExpect(status().isNoContent());
    }


}
