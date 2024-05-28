package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class CarShowroomContainer
{
    // Attributes
    Map<String, CarShowRoom> saloons = new HashMap<>();

    // Getters
    public Map<String, CarShowRoom> getSaloons()
    {
        return new HashMap<>(saloons);
    }

    // Methods
    void addCenter(String name, int amount)
    {
        List<Vehicle> l = new ArrayList<>();
        CarShowRoom csr = new CarShowRoom(name, l, amount);
        saloons.put(name, csr);
    }

    void removeCenter(String name)
    {
        if(saloons.containsKey(name))
        {
            saloons.remove(name);
        }
        else
        {
            System.err.println("No center");
        }
    }


    public List<String> getSortedSaloonsByLoad()
    {
        return saloons.entrySet().stream()
                .sorted(Comparator.comparingDouble(entry -> calculateLoad(entry.getValue())))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double calculateLoad(CarShowRoom showroom)
    {
        return (double) showroom.getVehicleCount() / showroom.maxVehicle;
    }
}
