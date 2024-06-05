package org.carshowroom.carshowroomclientapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;


@Entity
@Table (name = "carshowroom")
public class CarShowRoom implements Serializable
{
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "showroom")
    String showroom;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carShowRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Vehicle> carList;

    @Column(name = "maxVehicle")
    int maxVehicle;

    // Constructors
    public CarShowRoom(String showroom, List<Vehicle> carList, int maxVehicle)
    {
        this.showroom = showroom;
        this.carList = carList;
        this.maxVehicle = maxVehicle;
    }

    public CarShowRoom()
    {

    }

    // Getters
    public List<Vehicle> getVehicleList()
    {
        return new ArrayList<>(carList);
    }

    public int getVehicleCount() {
        return carList.size();
    }

    public String getName()
    {
        return this.showroom;
    }

    // Setters
    public void setName(String favorites)
    {
        this.showroom = favorites;
    }

    public void setVehicleList(List<Vehicle> vehicles)
    {
        this.carList = vehicles;
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
}
