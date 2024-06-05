package org.carshowroom.carshowroomclientapp;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "vehicle")
public class Vehicle implements Comparable<Vehicle>, Serializable
{
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @CsvColumn(name = "Brand", order = 1)
    @Column(name = "brand")
    private String brand;

    @CsvColumn(name = "Model", order = 2)
    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition")
    private ItemCondition condition;

    @CsvColumn(name = "Price", order = 3)
    @Column(name = "price")
    private double price;

    @CsvColumn(name = "Year of Production", order = 4)
    @Column(name = "yearOfProduction")
    private int yearOfProduction;

    @CsvColumn(name = "Mileage", order = 5)
    @Column(name = "mileage")
    private double mileage;

    @CsvColumn(name = "Engine Capacity", order = 6)
    @Column(name = "engineCapacity")
    private double engineCapacity;

    @CsvColumn(name = "quantity", order = 7)
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "carShowRoomID")
    private CarShowRoom carShowRoom;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;

    @Transient
    private long ratingCount;

    @Transient
    private double averageRating;


    // Constructors
    public Vehicle(String brand, String model, ItemCondition condition, double price, int yearOfProduction, double mileage, double engineCapacity, int quantity)
    {
        this.brand = brand;
        this.model = model;
        this.condition = condition;
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
        this.condition = other.condition;
        this.price = other.price;
        this.yearOfProduction = other.yearOfProduction;
        this.mileage = other.mileage;
        this.engineCapacity = other.engineCapacity;
        this.quantity = other.quantity;
    }

    public Vehicle()
    {
    }


    // Getters
    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }

    public ItemCondition getCondition()
    {
        return condition;
    }

    public int getYearOfProduction()
    {
        return yearOfProduction;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getPrice()
    {
        return price;
    }

    public double getEngineCapacity()
    {
        return engineCapacity;
    }

    public double getMileage()
    {
        return this.mileage;
    }

    public int getId()
    {
        return this.id;
    }

    public CarShowRoom getCarShowRoom()
    {
        return carShowRoom;
    }

    public List<Rating> getRatings()
    {
        return ratings;
    }

    public long getRatingCount()
    {
        return ratingCount;
    }

    public double getAverageRating()
    {
        return averageRating;
    }


    // Setters
    public void setCarShowRoom(CarShowRoom carShowRoom)
    {
        this.carShowRoom = carShowRoom;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setCondition(ItemCondition itemCondition)
    {
        this.condition = itemCondition;
    }

    public void setRatings(List<Rating> ratings)
    {
        this.ratings = ratings;
        updateRatingStatistics();
    }


    // Methods
    @Override
    public String toString()
    {
        return "Name: " + brand + " " + model +
                ", Price: $" + String.format("%.2f", price) +
                " Mileage: " + String.format("%.1f", mileage) + " km" +
                ", Engine Capacity: " + String.format("%.1f", engineCapacity) + " L";
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return Objects.equals(brand, vehicle.brand) &&
                Objects.equals(model, vehicle.model) &&
                yearOfProduction == vehicle.yearOfProduction &&
                Double.compare(vehicle.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, yearOfProduction, price);
    }


    @Override
    public int compareTo(Vehicle v1)
    {
        return this.brand.compareTo(v1.brand);
    }

    public String print()
    {
        System.out.println("Brand: " + this.brand);
        System.out.println("Model: " + this.model);
        System.out.println("Condition: " + this.condition);
        System.out.println("Price: " + this.price);
        System.out.println("Year of production: " + this.yearOfProduction);
        System.out.println("Mileage: " + this.mileage);
        System.out.println("Engine capacity: " + this.engineCapacity);
        System.out.println("Quantity: " + this.quantity + "\n");
        return null;
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
            this.averageRating = Math.round(this.averageRating * Math.pow(10, 2)) / Math.pow(10, 2);
        }
        else
        {
            this.ratingCount = 0;
            this.averageRating = 0.0;
        }
    }
}
