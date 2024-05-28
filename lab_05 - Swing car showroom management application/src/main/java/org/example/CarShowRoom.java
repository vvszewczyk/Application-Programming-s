package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarShowRoom
{
    // Attributes
    String showroom;
    List<Vehicle> carList;
    int maxVehicle;

    // Constructor
    public CarShowRoom(String showroom, List<Vehicle> carList, int maxVehicle)
    {
        this.showroom = showroom;
        this.carList = carList;
        this.maxVehicle = maxVehicle;
    }

    // Getters
    public List<Vehicle> getVehicleList()
    {
        return new ArrayList<>(carList);
    }

    public int getVehicleCount()
    {
        return carList.size();
    }


    // Methods
    public boolean addProduct(Vehicle v)
    {
        for (Vehicle existingVehicle : carList)
        {
            if (existingVehicle.getBrand().equals(v.getBrand()) && existingVehicle.getModel().equals(v.getModel()))
            {
                existingVehicle.setQuantity(existingVehicle.getQuantity() + 1);
                return true;
            }
        }

        if (carList.size() < maxVehicle)
        {
            carList.add(v);
            return true;
        }
        else
        {
            return false;
        }
    }


    void removeProduct(Vehicle v)
    {
        if(carList.contains(v))
        {
            carList.remove(v);
        }
        else
        {
            System.err.println("No car to delete");
        }
    }
}
