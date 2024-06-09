package org.pau.springbootapplication.Services;

import org.pau.springbootapplication.MainSoruce.CarShowRoom;
import org.pau.springbootapplication.MainSoruce.Vehicle;
import org.pau.springbootapplication.Repositories.CarShowRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarShowRoomService
{
    @Autowired
    private CarShowRoomRepository carShowRoomRepository;

    public void addShowroom(CarShowRoom showroom)
    {
        carShowRoomRepository.save(showroom);
    }

    public boolean deleteShowroom(Long id)
    {
        if (carShowRoomRepository.existsById(id))
        {
            carShowRoomRepository.deleteById(id);
            return true;
        }
        else
        {
            return false;
        }
    }

    public List<CarShowRoom> getAllShowrooms()
    {
        return carShowRoomRepository.findAll();
    }

    public List<Vehicle> getVehiclesInShowroom(Long id)
    {
        CarShowRoom showroom = carShowRoomRepository.findById(id).orElse(null);
        return showroom != null ? showroom.getCarList() : null;
    }

    public Double getShowroomFillPercentage(Long id)
    {
        CarShowRoom showroom = carShowRoomRepository.findById(id).orElse(null);
        if (showroom != null && showroom.getMaxVehicle() > 0)
        {
            int vehicleCount = showroom.getCarList().size();
            return (double) vehicleCount / showroom.getMaxVehicle() * 100;
        }
        return null;
    }
}
