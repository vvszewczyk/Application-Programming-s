package org.carshowroom.carshowroomclientapp;

import java.util.ArrayList;
import java.util.List;

public class CarShowRoom
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
    public List<Vehicle> getVehicleList()
    {
        return new ArrayList<>(carList);
    }

    public int getVehicleCount()
    {
        return carList.size();
    }

    Vehicle getVehicle(Vehicle v)
    {
        if(carList.contains(v) && v.quantity > 0)
        {
            v.quantity--;
            if(v.quantity <= 0)
            {
                removeProduct(v);
            }
            return v;
        }
        else
        {
            System.err.println("The requested vehicle is not available.");
            return null;
        }

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

    private void removeProduct(Vehicle v)
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
