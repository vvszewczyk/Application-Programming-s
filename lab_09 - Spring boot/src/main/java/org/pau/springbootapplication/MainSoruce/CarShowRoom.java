package org.pau.springbootapplication.MainSoruce;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "carshowroom")
public class CarShowRoom implements Serializable
{
    // Attributes and annotations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "showroom")
    @NotEmpty
    @Size(min = 1, max = 50)
    private String showroom;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carShowRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Vehicle> carList = new ArrayList<>();

    @Column(name = "maxVehicle")
    @NotNull
    private int maxVehicle;

    // Constructors
    public CarShowRoom(String showroom, List<Vehicle> carList, int maxVehicle)
    {
        this.showroom = showroom;
        this.carList = carList;
        this.maxVehicle = maxVehicle;
    }

    public CarShowRoom() {}

    // Getters and Setters
    public long getId()
    {
        return id;
    }

    public String getShowroom()
    {
        return showroom;
    }

    public void setShowroom(String showroom)
    {
        this.showroom = showroom;
    }

    public List<Vehicle> getCarList()
    {
        return carList;
    }

    public void setCarList(List<Vehicle> carList)
    {
        this.carList = carList;
    }

    public int getMaxVehicle()
    {
        return maxVehicle;
    }

    public void setMaxVehicle(int maxVehicle)
    {
        this.maxVehicle = maxVehicle;
    }

    public void setQuantity(int quantity)
    {
        this.maxVehicle = quantity;
    }
}

