package org.example;

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

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public double getMileage()
    {
        return this.mileage;
    }

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

    public void print()
    {
        System.out.println("Brand: " + this.brand);
        System.out.println("Model: " + this.model);
        System.out.println("Condition: " + this.condition);
        System.out.println("Price: " + this.price);
        System.out.println("Year of production: " + this.yearOfProduction);
        System.out.println("Mileage: " + this.mileage);
        System.out.println("Engine capacity: " + this.engineCapacity);
        System.out.println("Quantity: " + this.quantity + "\n");
    }

    @Override
    public int compareTo(Vehicle v1)
    {
        return this.brand.compareTo(v1.brand);
    }


}
