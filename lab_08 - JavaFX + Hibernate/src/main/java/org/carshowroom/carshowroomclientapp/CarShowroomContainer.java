package org.carshowroom.carshowroomclientapp;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class CarShowroomContainer implements Serializable
{
    // Attribute
    Map<String, CarShowRoom> saloons = new HashMap<>();


    // Getters
    public Map<String, CarShowRoom> getSaloons()
    {
        return new HashMap<>(saloons);
    }

    public CarShowRoom getSaloon(String favorites)
    {
        for(Map.Entry<String, CarShowRoom> entry : saloons.entrySet())
        {
            if(entry.getKey().equals(favorites))
            {
                return entry.getValue();
            }
        }

        return null;
    }

    // Methods
    void addCenter(String name, int amount)
    {
        List<Vehicle> l = new ArrayList<>();
        CarShowRoom csr = new CarShowRoom(name, l, amount);
        saloons.put(name, csr);
    }

    void addShowroom(CarShowRoom showroom) {
        saloons.put(showroom.getName(), showroom);
    }

    public void getSaloonInfo(String name)
    {
        CarShowRoom saloon = saloons.get(name);
        if (saloon != null)
        {
            System.out.println("Saloon information: " + name);
            System.out.println("Maximum number of vehicles: " + saloon.maxVehicle);
            System.out.println("List of vehicles:");
            if (!saloon.carList.isEmpty())
            {
                for (Vehicle v : saloon.carList)
                {
                    System.out.println("- " + v.getBrand() + " " + v.getModel() + ", Condition: " + v.getCondition() + ", Price: " + v.getPrice() + ", Year of production: " + v.getYearOfProduction() + ", Mileage: " + v.getMileage() + ", Engine capacity: " + v.getEngineCapacity() + ", Quantity: " + v.getQuantity());
                }
            }
            else
            {
                System.out.println("No vehicles in the saloon.");
            }
        }
        else
        {
            System.err.println("Saloon named " + name + " does not exist.");
        }
    }
}
