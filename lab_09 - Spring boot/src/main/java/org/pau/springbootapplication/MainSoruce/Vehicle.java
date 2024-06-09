package org.pau.springbootapplication.MainSoruce;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Comparable<Vehicle>, Serializable
{
    // Attributes and annotations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    @NotEmpty
    @Size(min = 1, max = 50)
    private String brand;

    @Column(name = "model")
    @NotEmpty
    @Size(min = 1, max = 50)
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "itemCondition")
    private ItemCondition itemCondition;

    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "yearOfProduction")
    @NotNull
    private int yearOfProduction;

    @Column(name = "mileage")
    @NotNull
    private double mileage;

    @Column(name = "engineCapacity")
    @NotNull
    private double engineCapacity;

    @Column(name = "quantity")
    @NotNull
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carShowRoomID")
    @JsonBackReference
    private CarShowRoom carShowRoom;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Rating> ratings = new ArrayList<>();

    @Transient
    private long ratingCount;

    @Transient
    private double averageRating;

    // Constructors
    public Vehicle(String brand, String model, ItemCondition condition, double price, int yearOfProduction, double mileage, double engineCapacity, int quantity)
    {
        this.brand = brand;
        this.model = model;
        this.itemCondition = condition;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.mileage = mileage;
        this.engineCapacity = engineCapacity;
        this.quantity = quantity;
    }

    public Vehicle(Vehicle other)
    {
        this.brand = other.brand;
        this.model = other.model;
        this.itemCondition = other.itemCondition;
        this.price = other.price;
        this.yearOfProduction = other.yearOfProduction;
        this.mileage = other.mileage;
        this.engineCapacity = other.engineCapacity;
        this.quantity = other.quantity;
    }

    public Vehicle() {}

    // Getters and Setters
    public int getId()
    {
        return id;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }

    public ItemCondition getItemCondition()
    {
        return itemCondition;
    }

    public double getPrice()
    {
        return price;
    }

    public int getYearOfProduction()
    {
        return yearOfProduction;
    }

    public double getMileage()
    {
        return mileage;
    }

    public double getEngineCapacity()
    {
        return engineCapacity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public CarShowRoom getCarShowRoom()
    {
        return carShowRoom;
    }

    public void setCarShowRoom(CarShowRoom carShowRoom)
    {
        this.carShowRoom = carShowRoom;
    }

    public List<Rating> getRatings()
    {
        return ratings;
    }

    public void setRatings(List<Rating> ratings)
    {
        this.ratings = ratings;
        updateRatingStatistics();
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    // Methods
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Vehicle vehicle = (Vehicle) obj;
        return yearOfProduction == vehicle.yearOfProduction && Double.compare(vehicle.price, price) == 0 && Objects.equals(brand, vehicle.brand) && Objects.equals(model, vehicle.model);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(brand, model, yearOfProduction, price);
    }

    @Override
    public int compareTo(Vehicle v1)
    {
        return this.brand.compareTo(v1.brand);
    }

    public void addRating(Rating rating)
    {
        ratings.add(rating);
        updateRatingStatistics();
    }

    public void updateRatingStatistics()
    {
        if (ratings != null && !ratings.isEmpty())
        {
            this.ratingCount = ratings.size();
            double sum = ratings.stream().mapToInt(Rating::getRating).sum();
            this.averageRating = (sum / ratingCount);
            this.averageRating = Math.round(this.averageRating * 100.0) / 100.0;
        }
        else
        {
            this.ratingCount = 0;
            this.averageRating = 0.0;
        }
    }
}

