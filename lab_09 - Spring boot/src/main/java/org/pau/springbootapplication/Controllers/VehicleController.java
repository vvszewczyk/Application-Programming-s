package org.pau.springbootapplication.Controllers;

import jakarta.validation.Valid;
import org.pau.springbootapplication.MainSoruce.Vehicle;
import org.pau.springbootapplication.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class VehicleController
{
    @Autowired
    private VehicleService vehicleService;

    // Handling vehicle events
    @PostMapping
    public ResponseEntity<Void> addVehicle(@Valid @RequestBody Vehicle vehicle)
    {
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id)
    {
        if (vehicleService.deleteVehicle(id))
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/rating")
    public ResponseEntity<Double> getVehicleRating(@PathVariable Long id)
    {
        Double rating = vehicleService.getVehicleRating(id);
        if (rating != null)
        {
            return ResponseEntity.ok(rating);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/csv")
    public ResponseEntity<Resource> getAllVehiclesCsv()
    {
        Resource csvFile = vehicleService.getAllVehiclesCsv();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"vehicles.csv\"")
                .body(csvFile);
    }
}
