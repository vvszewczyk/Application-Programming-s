package org.carshowroom.carshowroomclientapp;

import java.util.Objects;

public class Vehicle implements Comparable<Vehicle>
{
    String brand;
    String model;
    ItemCondition condition;
    double price;
    int yearOfProduction;
    double mileage;
    double engineCapacity;
    int quantity;

    public Vehicle(String brand, String model, ItemCondition condition, double price, int yearOfProduction, double mileage, double engineCapacity)
    {
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.mileage = mileage;
        this.engineCapacity = engineCapacity;
        this.quantity = 1;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public ItemCondition getCondition() {
        return condition;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public int getQuantity() {
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
    public int hashCode()
    {
        return Objects.hash(brand, model, yearOfProduction, price);
    }

    @Override
    public String toString()
    {
        return "Name: " + brand + " " + model +
                ", Price: $" + String.format("%.2f", price) +
                " Mileage: " + String.format("%.1f", mileage) + " km" +
                ", Engine Capacity: " + String.format("%.1f", engineCapacity) + " L";
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
}
