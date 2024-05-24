import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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


    void addProduct(Vehicle v)
    {
        boolean found = false;
        for (Vehicle existingVehicle : carList)
        {
            if (existingVehicle.brand.equals(v.brand) && existingVehicle.model.equals(v.model))
            {
                found = true;
                existingVehicle.quantity++;
                break;
            }
        }
        if(!found && carList.size() < maxVehicle)
        {
            carList.add(v);
        } else if(found)
        {
            System.out.println("Vehicle already exists and was updated.");
        } else
        {
            System.err.println("You exceed the limit of cars.");
        }
    }

    Vehicle getProduct(Vehicle v)
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

    void removeProduct(Vehicle v)
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

    public Vehicle search(String brandName)
    {
        //porównanie obiektów po polu brand
        Comparator<Vehicle> byBrand = Comparator.comparing(v -> v.brand);
        //przekształcenie listy w strumień
        return carList.stream()
                .filter(v -> v.brand.equalsIgnoreCase(brandName))
                .sorted(byBrand)
                .findFirst()
                .orElse(null);
    }

    public List<Vehicle> searchPartial(String partialName)
    {
        //przekształcenie listy w strumień
        return carList.stream()
                .filter(v -> v.brand.contains(partialName) || v.model.contains(partialName))
                .collect(Collectors.toList());
    }

    public long countByCondition(ItemCondition condition)
    {
        //przekształcenie listy w strumień
        return carList.stream()
                .filter(v -> v.condition == condition)
                .count();
    }

    public void summary()
    {
        carList.forEach(Vehicle::print);
    }

    public List<Vehicle> sortByName()
    {
        return carList.stream()
                .sorted(Comparator.comparing(v -> v.brand))
                .collect(Collectors.toList());
    }

    public List<Vehicle> sortByAmount()
    {
        return carList.stream()
                .sorted(Comparator.comparingInt(v -> -v.quantity)) //malejąco
                .collect(Collectors.toList());
    }

    public Vehicle max()
    {
        return Collections.max(carList, Comparator.comparingInt(v -> v.quantity));
    }


}
