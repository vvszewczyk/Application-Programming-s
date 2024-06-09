package org.pau.springbootapplication.Controllers;

import jakarta.validation.Valid;
import org.pau.springbootapplication.MainSoruce.CarShowRoom;
import org.pau.springbootapplication.MainSoruce.Vehicle;
import org.pau.springbootapplication.Services.CarShowRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fulfillment")
public class CarShowRoomController
{
    @Autowired
    private CarShowRoomService carShowRoomService;

    // Handle carshowroom events
    @PostMapping
    public ResponseEntity<Void> addShowroom(@Valid @RequestBody CarShowRoom showroom)
    {
        System.out.println("Received Showroom: " + showroom);
        carShowRoomService.addShowroom(showroom);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowroom(@PathVariable Long id)
    {
        if (carShowRoomService.deleteShowroom(id))
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CarShowRoom>> getAllShowrooms()
    {
        return ResponseEntity.ok(carShowRoomService.getAllShowrooms());
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Vehicle>> getVehiclesInShowroom(@PathVariable Long id)
    {
        List<Vehicle> vehicles = carShowRoomService.getVehiclesInShowroom(id);
        if (vehicles != null)
        {
            return ResponseEntity.ok(vehicles);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/fill")
    public ResponseEntity<Double> getShowroomFillPercentage(@PathVariable Long id)
    {
        Double fillPercentage = carShowRoomService.getShowroomFillPercentage(id);
        if (fillPercentage != null)
        {
            return ResponseEntity.ok(fillPercentage);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
