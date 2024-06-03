package org.carshowroom.carshowroomclientapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarShowRoom implements Serializable
{
    String showroom;
    List<Vehicle> carList;
    int maxVehicle;

    public CarShowRoom(String showroom, List<Vehicle> carList, int maxVehicle)
    {
        this.showroom = showroom;
        this.carList = carList;
        this.maxVehicle = maxVehicle;
    }
    public List<Vehicle> getVehicleList() {
        return new ArrayList<>(carList);
    }

    public int getVehicleCount() {
        return carList.size();
    }


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
}
