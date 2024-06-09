package org.pau.springbootapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.pau.springbootapplication.Controllers.CarShowRoomController;
import org.pau.springbootapplication.MainSoruce.CarShowRoom;
import org.pau.springbootapplication.Services.CarShowRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarShowRoomController.class)
public class CarShowRoomControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarShowRoomService carShowRoomService;

    private CarShowRoom showroom;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        showroom = new CarShowRoom("Central Showroom", Collections.emptyList(), 50);
    }

    @Test
    public void addShowroom_ShouldReturnCreatedStatus() throws Exception
    {
        doNothing().when(carShowRoomService).addShowroom(any(CarShowRoom.class));
        mockMvc.perform(post("/api/fulfillment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"showroom\": \"Central Showroom\", \"maxVehicle\": 50}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteShowroom_ShouldReturnNoContentStatus() throws Exception
    {
        when(carShowRoomService.deleteShowroom(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/fulfillment/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteShowroom_ShouldReturnNotFoundStatus() throws Exception
    {
        when(carShowRoomService.deleteShowroom(1L)).thenReturn(false);
        mockMvc.perform(delete("/api/fulfillment/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllShowrooms_ShouldReturnOkStatus() throws Exception
    {
        when(carShowRoomService.getAllShowrooms()).thenReturn(Collections.singletonList(showroom));
        mockMvc.perform(get("/api/fulfillment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].showroom").value("Central Showroom"));
    }

    @Test
    public void getVehiclesInShowroom_ShouldReturnOkStatus() throws Exception
    {
        when(carShowRoomService.getVehiclesInShowroom(1L)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/fulfillment/1/products"))
                .andExpect(status().isOk());
    }

    @Test
    public void getShowroomFillPercentage_ShouldReturnOkStatus() throws Exception
    {
        when(carShowRoomService.getShowroomFillPercentage(1L)).thenReturn(75.0);
        mockMvc.perform(get("/api/fulfillment/1/fill"))
                .andExpect(status().isOk())
                .andExpect(content().string("75.0"));
    }
}
