package org.carshowroom.carshowroomclientapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.carshowroom.carshowroomclientapp.ItemCondition.NEW;

public class RootController
{
    @FXML
    private ImageView logoImageView;
    @FXML
    private ComboBox<String> salonComboBox;
    @FXML
    private ComboBox<String> conditionComboBox;
    @FXML
    private TableView<Vehicle> vehicleTable;
    @FXML
    private TableColumn<Vehicle, String> brandColumn;
    @FXML
    private TableColumn<Vehicle, String> modelColumn;
    @FXML
    private TableColumn<Vehicle, Integer> yearColumn;
    @FXML
    private TableColumn<Vehicle, String> conditionColumn;
    @FXML
    private TableColumn<Vehicle, Double> priceColumn;
    @FXML
    private TableColumn<Vehicle, Double> engineCapacityColumn;
    @FXML
    private TableColumn<Vehicle, Double> mileageColumn;
    @FXML
    private TextField filterTextField;
    @FXML
    private ImageView vehicleImageView;
    @FXML
    private VBox box;
    @FXML
    private Label vehicleInfo;
    @FXML
    private Label salonInfo;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label locatio;
    @FXML
    private Label additionalInfo;
    @FXML
    private Label quantityInfo;


    private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();

    private static CarShowroomContainer container;

    public void initialize()
    {
        Image logoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/carshowroom/carshowroomclientapp/logo.png")));
        logoImageView.setImage(logoImage);

        loadData();
        if (container == null || container.getSaloons().isEmpty())
        {
            container = new CarShowroomContainer();
            addTestSalonsToContainer();
        }

        setupSalonComboBox();
        setupConditionComboBox();
        initializeTableColumns();
        setupFilterTextField();
        setupTableSelection();
        updateVehicleTable(salonComboBox.getSelectionModel().getSelectedItem());
    }


    private void initializeTableColumns()
    {
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("yearOfProduction"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        engineCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        vehicleTable.setRowFactory(tv -> new TableRow<Vehicle>()
        {
            private Tooltip tooltip = new Tooltip();

            @Override
            public void updateItem(Vehicle vehicle, boolean empty)
            {
                super.updateItem(vehicle, empty);
                if (vehicle == null || empty)
                {
                    setTooltip(null);
                } else
                {
                    String salonName = findSalonNameForVehicle(vehicle);
                    tooltip.setText(createTooltipText(vehicle, salonName));
                    setTooltip(tooltip);
                }
            }
        });
    }

    private String findSalonNameForVehicle(Vehicle vehicle)
    {
        for (Map.Entry<String, CarShowRoom> entry : container.getSaloons().entrySet())
        {
            if (entry.getValue().getVehicleList().contains(vehicle))
            {
                return entry.getKey();
            }
        }
        return "Nieznany salon";
    }

    private String createTooltipText(Vehicle vehicle, String salonName)
    {
        return "Car showroom: " + salonName + "\n" +
                "Mileage: " + vehicle.getMileage() + " km\n" +
                "Capacity: " + vehicle.getEngineCapacity() + " L";
    }


    private void setupSalonComboBox()
    {
        salonComboBox.getItems().clear();
        salonComboBox.getItems().add("All Salons");
        salonComboBox.getItems().addAll(container.getSaloons().keySet());
        salonComboBox.getItems().add("Favorites");

        salonComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            if ("Favorites".equals(newVal))
            {
                conditionComboBox.getSelectionModel().select("All Conditions");
                loadFavorites();
                updateVehicleTable(newVal);
            } else {
                System.out.println("Wybrano salon: " + newVal);
                updateVehicleTable(newVal);
            }
        });

        if (!salonComboBox.getItems().isEmpty())
        {
            salonComboBox.getSelectionModel().selectFirst();
        }
    }




    private void setupConditionComboBox()
    {
        conditionComboBox.getItems().clear();
        conditionComboBox.getItems().add("All Conditions");
        for (ItemCondition condition : ItemCondition.values())
        {
            conditionComboBox.getItems().add(condition.name());
        }
        conditionComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            updateVehicleTable(salonComboBox.getSelectionModel().getSelectedItem());
        });
        conditionComboBox.getSelectionModel().selectFirst();
        conditionComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateVehicleView());
    }

    private void updateVehicleTable(String salonName)
    {
        String selectedCondition = conditionComboBox.getSelectionModel().getSelectedItem();
        vehicleData.clear();

        if ("Favorites".equals(salonName))
        {
            List<Vehicle> favorites = loadFavorites();
            if ("All Conditions".equals(selectedCondition) || selectedCondition == null) {
                vehicleData.addAll(favorites);
            }
            else
            {
                ItemCondition condition = ItemCondition.valueOf(selectedCondition);
                vehicleData.addAll(filterByCondition(favorites, condition));
            }
        }
        else if ("All Salons".equals(salonName))
        {
            for (CarShowRoom salon : container.getSaloons().values())
            {
                if ("All Conditions".equals(selectedCondition) || selectedCondition == null)
                {
                    vehicleData.addAll(salon.getVehicleList());
                }
                else
                {
                    ItemCondition condition = ItemCondition.valueOf(selectedCondition);
                    vehicleData.addAll(filterByCondition(salon.getVehicleList(), condition));
                }
            }
        }
        else
        {
            CarShowRoom salon = container.getSaloons().get(salonName);
            if (salon != null)
            {
                List<Vehicle> vehicles = salon.getVehicleList();
                if ("All Conditions".equals(selectedCondition) || selectedCondition == null)
                {
                    vehicleData.addAll(vehicles);
                }
                else
                {
                    ItemCondition condition = ItemCondition.valueOf(selectedCondition);
                    vehicleData.addAll(filterByCondition(vehicles, condition));
                }
            }
        }

        vehicleTable.setItems(vehicleData);
        vehicleTable.refresh();
        System.out.println("Tabela została zaktualizowana dla salonu: " + salonName + ", liczba pojazdów: " + vehicleData.size());
    }



    private void updateVehicleView()
    {
        String searchText = filterTextField.getText();
        String selectedSalon = salonComboBox.getSelectionModel().getSelectedItem();
        String selectedCondition = conditionComboBox.getSelectionModel().getSelectedItem();


        Stream<Vehicle> stream = getVehicleStream(selectedSalon, selectedCondition);

        if (searchText != null && !searchText.isEmpty()) {
            String lowerCaseSearchText = searchText.toLowerCase();
            stream = stream.filter(v -> v.getBrand().toLowerCase().contains(lowerCaseSearchText) ||
                    v.getModel().toLowerCase().contains(lowerCaseSearchText));
        }

        vehicleTable.setItems(stream.collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }


    private Stream<Vehicle> getVehicleStream(String salonName, String condition)
    {
        Stream<Vehicle> stream;

        if ("All Salons".equals(salonName))
        {
            stream = container.getSaloons().values().stream()
                    .flatMap(salon -> salon.getVehicleList().stream());
        }
        else if("Favorites".equals(salonName))
        {
            List<Vehicle> favorites = loadFavorites();
            stream = favorites.stream();
        }
        else
        {
            CarShowRoom salon = container.getSaloons().get(salonName);
            if (salon != null)
            {
                stream = salon.getVehicleList().stream();
            }
            else
            {
                stream = Stream.empty();
            }
        }

        if (!"All Conditions".equals(condition))
        {
            ItemCondition itemCondition = ItemCondition.valueOf(condition);
            stream = stream.filter(v -> v.getCondition().equals(itemCondition));
        }

        return stream;
    }


    private List<Vehicle> filterByCondition(List<Vehicle> vehicles, ItemCondition condition)
    {
        if (condition == null)
        {
            return vehicles;
        }
        return vehicles.stream()
                .filter(v -> v.getCondition() == condition)
                .collect(Collectors.toList());
    }

    private void setupFilterTextField()
    {
        filterTextField.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue.isEmpty())
            {
                updateVehicleView();
            }
            else
            {
                filterVehicles(newValue);
            }
        });
    }



    private void filterVehicles(String searchText)
    {
        String selectedSalon = salonComboBox.getSelectionModel().getSelectedItem();
        String selectedCondition = conditionComboBox.getSelectionModel().getSelectedItem();

        String lowerCaseSearchText = searchText.toLowerCase().trim();
        ObservableList<Vehicle> filteredData = FXCollections.observableArrayList();

        List<Vehicle> vehiclesToFilter;

        if ("Favorites".equals(selectedSalon))
        {
            vehiclesToFilter = loadFavorites();
        }
        else if ("All Salons".equals(selectedSalon))
        {
            vehiclesToFilter = container.getSaloons().values().stream()
                    .flatMap(salon -> salon.getVehicleList().stream())
                    .collect(Collectors.toList());
        }
        else
        {
            CarShowRoom salon = container.getSaloons().get(selectedSalon);
            vehiclesToFilter = (salon != null) ? new ArrayList<>(salon.getVehicleList()) : new ArrayList<>();
        }

        if (!"All Conditions".equals(selectedCondition) && selectedCondition != null)
        {
            try
            {
                ItemCondition condition = ItemCondition.valueOf(selectedCondition);
                vehiclesToFilter = vehiclesToFilter.stream()
                        .filter(v -> v.getCondition() == condition)
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e)
            {
                System.err.println("Invalid condition selected: " + selectedCondition);
            }
        }

        if (!searchText.isEmpty())
        {
            vehiclesToFilter = vehiclesToFilter.stream()
                    .filter(v -> v.getBrand().toLowerCase().contains(lowerCaseSearchText) ||
                            v.getModel().toLowerCase().contains(lowerCaseSearchText))
                    .collect(Collectors.toList());
        }

        filteredData.setAll(vehiclesToFilter);
        vehicleTable.setItems(filteredData);
        vehicleTable.refresh();
    }


    private void setupTableSelection()
    {
        vehicleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            if (newSelection != null)
            {
                updateVehicleImage(newSelection);
                updateDetails(newSelection);
            }
        });
    }


    private void updateDetails(Vehicle vehicle)
    {
        box.setVisible(true);
        String salonName = findSalonNameForVehicle(vehicle);
        vehicleInfo.setText("Vehicle: " + vehicle.toString());
        quantityInfo.setText("Quantity: " + vehicle.getQuantity());
        salonInfo.setText("Salon: " + salonName);
        phoneNumber.setText("Phone: " + getSalonPhoneNumber(salonName));
        locatio.setText("Location: " + getSalonLocation(salonName));
        additionalInfo.setText("More Info: For more details visit https://www.otomoto.pl/");
    }


    private String getSalonPhoneNumber(String salonName)
    {
        return "123-456-789";
    }

    private String getSalonLocation(String salonName)
    {
        return "City Center";
    }

    private void updateVehicleImage(Vehicle vehicle)
    {
        String imageName = vehicle.getBrand().toLowerCase() + "_" + vehicle.getModel().toLowerCase() + ".png";
        String imagePath = "/org/carshowroom/carshowroomclientapp/images/" + imageName;
        System.out.println("Ładowanie obrazu z: " + imagePath);
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        if (image.isError())
        {
            System.out.println("Błąd podczas ładowania obrazu.");
        }
        else
        {
            vehicleImageView.setImage(image);
            System.out.println("Obraz załadowany poprawnie.");
        }
    }


    @FXML
    private void handleBuyAction()
    {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null && selectedVehicle.getQuantity() > 0)
        {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Purchase Vehicle");
            dialog.setHeaderText("Enter your details to reserve the " + selectedVehicle.getBrand() + " " + selectedVehicle.getModel());
            dialog.setContentText("Please enter your name:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name ->
            {
                selectedVehicle.setQuantity(selectedVehicle.getQuantity() - 1);
                if ("Favorites".equals(salonComboBox.getSelectionModel().getSelectedItem()))
                {
                    updateFavoritesFile();
                }
                updateVehicleTable(salonComboBox.getSelectionModel().getSelectedItem()); // Refresh view
                vehicleTable.refresh();
                updateDetails(selectedVehicle);
                saveData();
                showAlert(Alert.AlertType.INFORMATION, "Purchase booking successful", "Thank you " + name + ", your booking has been confirmed! Remaining quantity: " + selectedVehicle.getQuantity());
            });
        }
        else
        {
            showAlert(Alert.AlertType.WARNING, "No Selection or No Stock", "Please select a vehicle to purchase or vehicle is out of stock.");
        }
    }


    private void updateFavoritesFile()
    {
        List<Vehicle> updatedFavorites = new ArrayList<>();
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        boolean updated = false;

        try
        {
            List<Vehicle> currentFavorites = loadFavorites();
            for (Vehicle vehicle : currentFavorites)
            {
                if (vehicle.equals(selectedVehicle))
                {
                    vehicle.setQuantity(selectedVehicle.getQuantity());
                    updated = true;
                }
                updatedFavorites.add(vehicle);
            }

            if (updated)
            {
                saveFavorites(updatedFavorites);
            }
        }
        catch (Exception e)
        {
            System.err.println("Failed to update favorites file: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update the favorites file.");
        }
    }


    @FXML
    private void handleAddToFavorites()
    {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            if (!isVehicleInFavorites(selectedVehicle))
            {
                try (FileWriter writer = new FileWriter("favorites.csv", true))
                {
                    writer.append(String.join(",",
                                    selectedVehicle.getBrand(),
                                    selectedVehicle.getModel(),
                                    String.valueOf(selectedVehicle.getPrice()),
                                    String.valueOf(selectedVehicle.getYearOfProduction()),
                                    String.valueOf(selectedVehicle.getMileage()),
                                    String.valueOf(selectedVehicle.getEngineCapacity()),
                                    String.valueOf(selectedVehicle.getQuantity())))
                            .append("\n");
                    writer.flush();
                    showAlert(Alert.AlertType.INFORMATION, "Added to Favorites", "The vehicle has been added to your favorites.");
                }
                catch (IOException e)
                {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to add the vehicle to favorites: " + e.getMessage());
                }
            }
            else
            {
                showAlert(Alert.AlertType.WARNING, "Already in Favorites", "This vehicle is already in your favorites.");
            }
        }
        else
        {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a vehicle to add to favorites.");
        }
    }



    private boolean isVehicleInFavorites(Vehicle vehicle)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("favorites.csv")))
        {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(",");
                if (data.length >= 7) {
                    String brand = data[0];
                    String model = data[1];
                    double price = Double.parseDouble(data[2]);
                    int yearOfProduction = Integer.parseInt(data[3]);
                    double mileage = Double.parseDouble(data[4]);
                    double engineCapacity = Double.parseDouble(data[5]);
                    int quantity = Integer.parseInt(data[6]);

                    if (brand.equals(vehicle.getBrand()) && model.equals(vehicle.getModel()) &&
                            price == vehicle.getPrice() && yearOfProduction == vehicle.getYearOfProduction() &&
                            mileage == vehicle.getMileage() && engineCapacity == vehicle.getEngineCapacity() &&
                            quantity == vehicle.getQuantity())
                    {
                        return true;
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error while reading from favorites file: " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            System.err.println("Error in parsing number from CSV: " + e.getMessage());
        }
        return false;
    }


    private List<Vehicle> loadFavorites()
    {
        List<Vehicle> favorites = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("favorites.csv")))
        {
            String line = reader.readLine();
            if (line == null)
            {
                return favorites;
            }

            Map<String, Field> fieldMap = Arrays.stream(Vehicle.class.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(CsvColumn.class))
                    .collect(Collectors.toMap(
                            f -> f.getAnnotation(CsvColumn.class).name(),
                            f -> f
                    ));

            String[] headers = line.split(",");
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++)
            {
                headerMap.put(headers[i].trim(), i);
            }

            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(",");
                Vehicle vehicle = new Vehicle();
                boolean valid = true;
                for (Map.Entry<String, Field> entry : fieldMap.entrySet())
                {
                    Field field = entry.getValue();
                    Integer index = headerMap.get(entry.getKey());
                    if (index == null || index >= data.length)
                    {
                        System.err.println("Missing data for " + entry.getKey());
                        valid = false;
                        break;
                    }
                    field.setAccessible(true);
                    Object value = convert(field.getType(), data[index]);
                    field.set(vehicle, value);
                    vehicle.setCondition(NEW);
                }
                if (valid)
                {
                    favorites.add(vehicle);
                }
            }
        }
        catch (IOException | IllegalAccessException | IllegalArgumentException e)
        {
            System.err.println("Error loading from CSV: " + e.getMessage());
        }
        return favorites;
    }


    private Object convert(Class<?> type, String data) throws IllegalArgumentException
    {
        if (type == int.class || type == Integer.class) return Integer.parseInt(data);
        if (type == double.class || type == Double.class) return Double.parseDouble(data);
        if (type == String.class) return data;
        throw new IllegalArgumentException("Unsupported field type");
    }



    @FXML
    private void handleRemoveFromFavorites()
    {
        if (!"Favorites".equals(salonComboBox.getSelectionModel().getSelectedItem()))
        {
            showAlert(Alert.AlertType.WARNING, "Incorrect Tab", "You can only remove vehicles from favorites when in the 'Favorites' tab.");
            return;
        }

        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null)
        {
            List<Vehicle> vehicles = loadFavorites(); // Wczytaj aktualną listę ulubionych
            vehicles.removeIf(v -> isSameVehicle(v, selectedVehicle)); // Usuń zaznaczony pojazd
            saveFavorites(vehicles); // Zapisz zmodyfikowaną listę do pliku
            loadFavorites(); // Ponownie wczytaj listę ulubionych do tabeli
            updateVehicleTable("Favorites"); // Odśwież tabelę z ulubionymi
            vehicleTable.refresh(); // Dodatkowe odświeżenie widoku tabeli
        }
        else
        {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a vehicle to remove from favorites.");
        }
    }


    private boolean isSameVehicle(Vehicle v1, Vehicle v2)
    {
        return v1.getBrand().equals(v2.getBrand()) && v1.getModel().equals(v2.getModel()) &&
                v1.getCondition() == v2.getCondition() && v1.getYearOfProduction() == v2.getYearOfProduction();
    }



    private void saveFavorites(List<Vehicle> vehicles)
    {
        try (FileWriter writer = new FileWriter("favorites.csv"))
        {
            List<Field> fields = Arrays.stream(Vehicle.class.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(CsvColumn.class))
                    .sorted(Comparator.comparingInt(f -> f.getAnnotation(CsvColumn.class).order()))
                    .collect(Collectors.toList());

            String header = fields.stream()
                    .map(f -> f.getAnnotation(CsvColumn.class).name())
                    .collect(Collectors.joining(","));
            writer.append(header + "\n");

            for (Vehicle vehicle : vehicles)
            {
                String line = fields.stream()
                        .map(f ->
                        {
                            try
                            {
                                f.setAccessible(true);
                                return f.get(vehicle).toString();
                            }
                            catch (IllegalAccessException e)
                            {
                                return "ERROR";
                            }
                        })
                        .collect(Collectors.joining(","));
                writer.append(line + "\n");
            }
            writer.flush();
        }
        catch (IOException e)
        {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }


    private void saveData()
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser")))
        {
            out.writeObject(container);
        } catch (IOException e)
        {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Save Error", "Failed to save data.");
        }
    }

    private void loadData()
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser")))
        {
            container = (CarShowroomContainer) in.readObject();
            for (CarShowRoom showroom : container.getSaloons().values())
            {
                for (Vehicle vehicle : showroom.getVehicleList())
                {
                    if (vehicle.getCondition() == null)
                    {
                        vehicle.setCondition(NEW);
                    }
                }
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.err.println("Failed to load data: " + e.getMessage());
            container = new CarShowroomContainer();
        }
    }
    

    private void addTestSalonsToContainer()
    {
        container.addCenter("Salon Kraków", 10);
        container.addCenter("Salon Warszawa", 15);
        container.addCenter("Salon Wrocław", 20);

        CarShowRoom salon1 = container.getSaloons().get("Salon Kraków");
        if (salon1 != null)
        {
            salon1.addProduct(new Vehicle("Toyota", "Corolla", NEW, 50000, 2020, 100, 1.8, 1));
            salon1.addProduct(new Vehicle("Ford", "Focus", ItemCondition.USED, 30000, 2018, 120, 2.0, 1));
            salon1.addProduct(new Vehicle("Volkswagen", "Golf", NEW, 45000, 2021, 110, 1.5, 1));
            salon1.addProduct(new Vehicle("Fiat", "500", ItemCondition.DAMAGED, 10000, 2015, 90, 1.2, 1));
            salon1.addProduct(new Vehicle("Renault", "Clio", NEW, 40000, 2021, 80, 1.5, 1));
            salon1.addProduct(new Vehicle("Chevrolet", "Cruze", ItemCondition.USED, 15000, 2016, 140, 1.8, 1));
            salon1.addProduct(new Vehicle("Mazda", "3", NEW, 55000, 2022, 120, 2.0, 1));
            salon1.addProduct(new Vehicle("Kia", "Rio", ItemCondition.DAMAGED, 7000, 2013, 75, 1.4, 1));
            salon1.addProduct(new Vehicle("Hyundai", "i30", ItemCondition.USED, 22000, 2017, 100, 1.6, 1));

        }

        CarShowRoom salon2 = container.getSaloons().get("Salon Warszawa");
        if (salon2 != null)
        {
            salon2.addProduct(new Vehicle("Audi", "A4", NEW, 60000, 2021, 150, 2.0, 1));
            salon2.addProduct(new Vehicle("BMW", "3 Series", ItemCondition.USED, 55000, 2019, 140, 2.0, 1));
            salon2.addProduct(new Vehicle("Peugeot", "308", ItemCondition.DAMAGED, 20000, 2016, 110, 1.6, 1));
            salon2.addProduct(new Vehicle("Nissan", "Qashqai", NEW, 50000, 2020, 117, 1.3, 1));
            salon2.addProduct(new Vehicle("Volvo", "XC60", NEW, 250000, 2022, 190, 2.0, 1));
            salon2.addProduct(new Vehicle("Opel", "Astra", ItemCondition.USED, 32000, 2018, 110, 1.6, 1));
            salon2.addProduct(new Vehicle("Toyota", "Yaris", ItemCondition.DAMAGED, 5000, 2012, 90, 1.33, 1));
            salon2.addProduct(new Vehicle("Ford", "Mustang", NEW, 85000, 2021, 300, 5.0, 1));
            salon2.addProduct(new Vehicle("Peugeot", "207", ItemCondition.USED, 11000, 2015, 95, 1.4, 1));

        }

        CarShowRoom salon3 = container.getSaloons().get("Salon Wrocław");
        if (salon3 != null)
        {
            salon3.addProduct(new Vehicle("Lexus", "IS", ItemCondition.USED, 65000, 2018, 130, 2.5, 1));
            salon3.addProduct(new Vehicle("Honda", "Civic", NEW, 35000, 2020, 120, 1.5, 1));
            salon3.addProduct(new Vehicle("Skoda", "Octavia", ItemCondition.DAMAGED, 15000, 2017, 105, 1.4, 1));
            salon3.addProduct(new Vehicle("Alfa Romeo", "Giulia", NEW, 100000, 2021, 200, 2.9, 1));
            salon3.addProduct(new Vehicle("Tesla", "Model 3", NEW, 160000, 2022, 0, 0, 1)); // Electric
        }

        System.out.println("Dodano " + salon1.getVehicleList().size() + " pojazdów do salonu Kraków.");
        System.out.println("Dodano " + salon2.getVehicleList().size() + " pojazdów do salonu Warszawa.");
        System.out.println("Dodano " + salon3.getVehicleList().size() + " pojazdów do salonu Wrocław.");

    }

    private void showAlert(Alert.AlertType alertType, String title, String message)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}