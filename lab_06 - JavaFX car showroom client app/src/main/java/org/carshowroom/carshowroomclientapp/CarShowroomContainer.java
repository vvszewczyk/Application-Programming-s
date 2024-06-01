package org.carshowroom.carshowroomclientapp;

import java.util.*;
import java.util.stream.Collectors;

public class CarShowroomContainer
{
    Map<String, CarShowRoom> saloons = new HashMap<>();

    public Map<String, CarShowRoom> getSaloons()
    {
        return new HashMap<>(saloons);
    }

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

    List<CarShowRoom> findEmpty()
    {
        List<CarShowRoom> csr = new ArrayList<>();
        {
            for(Map.Entry<String, CarShowRoom> showroom : saloons.entrySet())
            {
                if(showroom.getValue().carList.isEmpty())
                {
                    csr.add(showroom.getValue());
                }
            }
            return csr;
        }
    }

    void summary()
    {
        for (Map.Entry<String, CarShowRoom> showroom : saloons.entrySet())
        {
            String salonName = showroom.getKey();
            CarShowRoom csr = showroom.getValue();
            int maxVehicle = csr.maxVehicle;
            int currentVehicleCount = csr.carList.size();
            double percentageFull = 0;
            if (maxVehicle > 0)
            {
                percentageFull = (currentVehicleCount / (double) maxVehicle) * 100;
            }

            System.out.println("Saloon name: " + salonName + ", cars in percentage: " + percentageFull + "%");
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
                    System.out.println("- " + v.brand + " " + v.model + ", Condition: " + v.condition + ", Price: " + v.price + ", Year of production: " + v.yearOfProduction + ", Mileage: " + v.mileage + ", Engine capacity: " + v.engineCapacity + ", Quantity: " + v.quantity);
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
