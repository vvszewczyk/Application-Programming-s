package org.pau.springbootapplication.Services;

import jakarta.transaction.Transactional;
import org.pau.springbootapplication.MainSoruce.CarShowRoom;
import org.pau.springbootapplication.MainSoruce.Rating;
import org.pau.springbootapplication.MainSoruce.Vehicle;
import org.pau.springbootapplication.Repositories.CarShowRoomRepository;
import org.pau.springbootapplication.Repositories.RatingRepository;
import org.pau.springbootapplication.Repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService
{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CarShowRoomRepository carShowRoomRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Transactional
    public void addVehicle(Vehicle vehicle)
    {
        CarShowRoom carShowRoom = carShowRoomRepository.findById(vehicle.getCarShowRoom().getId())
                .orElseThrow(() -> new RuntimeException("CarShowRoom not found"));

        vehicle.setCarShowRoom(carShowRoom);
        vehicleRepository.save(vehicle);
    }

    public boolean deleteVehicle(Long id)
    {
        if (vehicleRepository.existsById(id))
        {
            vehicleRepository.deleteById(id);
            System.out.println("Vehicle with ID " + id + " has been deleted.");
            return true;
        }
        else
        {
            System.out.println("Vehicle with ID  " + id + " does not exist.");
            return false;
        }
    }


    public Double getVehicleRating(Long id)
    {
        List<Rating> ratings = ratingRepository.findByVehicleId(id);
        if (ratings.isEmpty())
        {
            return null;
        }
        double averageRating = ratings.stream()
                .mapToInt(Rating::getRating)
                .average()
                .orElse(0.0);
        return averageRating;
    }

    public Resource getAllVehiclesCsv()
    {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        String csvContent = vehicles.stream()
                .map(vehicle -> String.format("%d,%s,%s,%s,%f,%d,%f,%f,%d",
                        vehicle.getId(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getItemCondition(),
                        vehicle.getPrice(),
                        vehicle.getYearOfProduction(),
                        vehicle.getMileage(),
                        vehicle.getEngineCapacity(),
                        vehicle.getQuantity()))
                .collect(Collectors.joining("\n", "id,brand,model,itemCondition,price,yearOfProduction,mileage,engineCapacity,quantity\n", ""));
        return new ByteArrayResource(csvContent.getBytes());
    }
}
