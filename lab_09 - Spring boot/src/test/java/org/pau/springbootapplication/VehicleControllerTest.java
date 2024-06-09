package org.pau.springbootapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.pau.springbootapplication.MainSoruce.ItemCondition;
import org.pau.springbootapplication.MainSoruce.Vehicle;
import org.pau.springbootapplication.Services.VehicleService;
import org.pau.springbootapplication.Services.CarShowRoomService;
import org.pau.springbootapplication.Controllers.VehicleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @MockBean
    private CarShowRoomService carShowRoomService;

    private Vehicle vehicle;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        vehicle = new Vehicle("Toyota", "Camry", ItemCondition.NEW, 20000, 2020, 0, 2.5, 5);
    }

    @Test
    public void addVehicle_ShouldReturnCreatedStatus() throws Exception
    {
        doNothing().when(vehicleService).addVehicle(any(Vehicle.class));
        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brand\": \"Toyota\", \"model\": \"Camry\", \"itemCondition\": \"NEW\", \"price\": 20000, \"yearOfProduction\": 2020, \"mileage\": 0, \"engineCapacity\": 2.5, \"quantity\": 5, \"carShowRoom\": {\"id\": 1}}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteVehicle_ShouldReturnNoContentStatus() throws Exception
    {
        when(vehicleService.deleteVehicle(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/product/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteVehicle_ShouldReturnNotFoundStatus() throws Exception
    {
        when(vehicleService.deleteVehicle(1L)).thenReturn(false);
        mockMvc.perform(delete("/api/product/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getVehicleRating_ShouldReturnOkStatus() throws Exception
    {
        when(vehicleService.getVehicleRating(1L)).thenReturn(4.5);
        mockMvc.perform(get("/api/product/1/rating"))
                .andExpect(status().isOk())
                .andExpect(content().string("4.5"));
    }

    @Test
    public void getVehicleRating_ShouldReturnNotFoundStatus() throws Exception
    {
        when(vehicleService.getVehicleRating(1L)).thenReturn(null);
        mockMvc.perform(get("/api/product/1/rating"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllVehiclesCsv_ShouldReturnOkStatus() throws Exception
    {
        when(vehicleService.getAllVehiclesCsv()).thenReturn(new ByteArrayResource("id,brand,model\n1,Toyota,Camry".getBytes()));
        mockMvc.perform(get("/api/product/csv"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"vehicles.csv\""));
    }
}