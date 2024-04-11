import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        //Inicjalizacja kontenera salonów samochodowych
        CarShowroomContainer container = new CarShowroomContainer();

        //Dodawanie salonów
        container.addCenter("Showroom Warszawa", 10);
        container.addCenter("Showroom Kraków", 5);
        container.addCenter("Showroom Wrocław", 15);

        //Tworzenie pojazdów
        Vehicle vehicle1 = new Vehicle("Toyota", "Corolla", ItemCondition.NEW, 20000, 2020, 0, 1.8);
        Vehicle vehicle2 = new Vehicle("Subaru", "Impreza", ItemCondition.USED, 15000, 2018, 20000, 2.0);
        Vehicle vehicle3 = new Vehicle("Toyota", "Supra", ItemCondition.NEW, 18000, 2021, 0, 2.5);

        //Dodawanie pojazdów do salonów
        container.saloons.get("Showroom Warszawa").addProduct(vehicle3);
        container.saloons.get("Showroom Warszawa").addProduct(vehicle2);
        container.saloons.get("Showroom Wrocław").addProduct(vehicle1);
        container.saloons.get("Showroom Wrocław").addProduct(vehicle2);

        //Dodanie tego samego pojazdu ponownie, aby zwiększyć jego ilość
        container.saloons.get("Showroom Warszawa").addProduct(vehicle3);


        //Wyszukiwanie pojazdu po marce
        Vehicle searchResult = container.saloons.get("Showroom Warszawa").search("Toyota");
        if (searchResult != null)
        {
            System.out.println("Toyota has been found:");
            searchResult.print();
        }

        //Wyszukiwanie pojazdów zawierających fragment nazwy
        System.out.println("Partial search result:");
        List<Vehicle> partialSearchResults = container.saloons.get("Showroom Warszawa").searchPartial("Impre");
        partialSearchResults.forEach(Vehicle::print);


        //Zliczanie pojazdów według stanu
        long countNew = container.saloons.get("Showroom Warszawa").countByCondition(ItemCondition.NEW);
        System.out.println("Number of new cars: " + countNew + "\n");

        //Wyświetlanie podsumowania wszystkich samochodów w salonie
        System.out.println("Summary of Showroom Warszawa:");
        container.saloons.get("Showroom Warszawa").summary();

        //Sortowanie pojazdów według nazwy i ilości
        System.out.println("Cars sorted by brand:");
        List<Vehicle> sortedByName = container.saloons.get("Showroom Warszawa").sortByName();
        sortedByName.forEach(Vehicle::print);

        System.out.println("Cars sorted by amount:");
        List<Vehicle> sortedByAmount = container.saloons.get("Showroom Warszawa").sortByAmount();
        sortedByAmount.forEach(Vehicle::print);

        //Znajdowanie pojazdu z największą ilością
        Vehicle maxVehicle = container.saloons.get("Showroom Warszawa").max();
        System.out.println("Car with highest availability:");
        maxVehicle.print();

        // Pobieranie pojazdu (zakładamy zakup), co zmniejsza jego ilość lub usuwa z listy
        Vehicle boughtVehicle = container.saloons.get("Showroom Warszawa").getProduct(vehicle2);
        if (boughtVehicle != null)
        {
            System.out.println("Car purchased:");
            boughtVehicle.print();
        }

        //Usuwanie pojazdu bezpośrednio tylko, gdy amount = 0
        //container.saloons.get("Showroom Warszawa").removeProduct(vehicle2); // Pojazd nie został dodany, więc drukuje error

        //Wyświetlenie informacji o salonie
        container.getSaloonInfo("Showroom Warszawa");


        // Wyświetlanie pustych salonów
        System.out.println("Empty showrooms:");
        List<CarShowRoom> emptyShowrooms = container.findEmpty();
        emptyShowrooms.forEach(showroom -> System.out.println(showroom.showroom));

        //Usuwanie salonu
        container.removeCenter("Showroom Kraków");

        // Podsumowanie informacji o wszystkich salonach
        container.summary();


    }
}
