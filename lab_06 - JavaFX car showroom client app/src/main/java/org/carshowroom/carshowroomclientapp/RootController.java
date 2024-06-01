package org.carshowroom.carshowroomclientapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private Label locatio; // 'location' name is reserved 
    @FXML
    private Label additionalInfo;
    @FXML
    private Label quantityInfo;


    private final ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();
    private CarShowroomContainer container;

    public void initialize()
    {
        Image logoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/carshowroom/carshowroomclientapp/logo.png")));
        logoImageView.setImage(logoImage);
        container = new CarShowroomContainer();
        addTestSalonsToContainer();
        setupSalonComboBox();
        setupConditionComboBox();
        initializeTableColumns();
        setupFilterTextField();
        setupTableSelection();
        vehicleTable.setItems(vehicleData);
    }

    private void addTestSalonsToContainer()
    {
        container.addCenter("Salon Kraków", 10);
        container.addCenter("Salon Warszawa", 15);
        container.addCenter("Salon Wrocław", 20);

        CarShowRoom salon1 = container.getSaloons().get("Salon Kraków");
        if (salon1 != null)
        {
            salon1.addProduct(new Vehicle("Toyota", "Corolla", ItemCondition.NEW, 50000, 2020, 100, 1.8));
            salon1.addProduct(new Vehicle("Ford", "Focus", ItemCondition.USED, 30000, 2018, 120, 2.0));
            salon1.addProduct(new Vehicle("Volkswagen", "Golf", ItemCondition.NEW, 45000, 2021, 110, 1.5));
            salon1.addProduct(new Vehicle("Fiat", "500", ItemCondition.DAMAGED, 10000, 2015, 90, 1.2));
            salon1.addProduct(new Vehicle("Renault", "Clio", ItemCondition.NEW, 40000, 2021, 80, 1.5));
            salon1.addProduct(new Vehicle("Chevrolet", "Cruze", ItemCondition.USED, 15000, 2016, 140, 1.8));
            salon1.addProduct(new Vehicle("Mazda", "3", ItemCondition.NEW, 55000, 2022, 120, 2.0));
            salon1.addProduct(new Vehicle("Kia", "Rio", ItemCondition.DAMAGED, 7000, 2013, 75, 1.4));
            salon1.addProduct(new Vehicle("Hyundai", "i30", ItemCondition.USED, 22000, 2017, 100, 1.6));

        }

        CarShowRoom salon2 = container.getSaloons().get("Salon Warszawa");
        if (salon2 != null)
        {
            salon2.addProduct(new Vehicle("Audi", "A4", ItemCondition.NEW, 60000, 2021, 150, 2.0));
            salon2.addProduct(new Vehicle("BMW", "3 Series", ItemCondition.USED, 55000, 2019, 140, 2.0));
            salon2.addProduct(new Vehicle("Peugeot", "308", ItemCondition.DAMAGED, 20000, 2016, 110, 1.6));
            salon2.addProduct(new Vehicle("Nissan", "Qashqai", ItemCondition.NEW, 50000, 2020, 117, 1.3));
            salon2.addProduct(new Vehicle("Volvo", "XC60", ItemCondition.NEW, 250000, 2022, 190, 2.0));
            salon2.addProduct(new Vehicle("Opel", "Astra", ItemCondition.USED, 32000, 2018, 110, 1.6));
            salon2.addProduct(new Vehicle("Toyota", "Yaris", ItemCondition.DAMAGED, 5000, 2012, 90, 1.33));
            salon2.addProduct(new Vehicle("Ford", "Mustang", ItemCondition.NEW, 85000, 2021, 300, 5.0));
            salon2.addProduct(new Vehicle("Peugeot", "207", ItemCondition.USED, 11000, 2015, 95, 1.4));

        }

        CarShowRoom salon3 = container.getSaloons().get("Salon Wrocław");
        if (salon3 != null)
        {
            salon3.addProduct(new Vehicle("Lexus", "IS", ItemCondition.USED, 65000, 2018, 130, 2.5));
            salon3.addProduct(new Vehicle("Honda", "Civic", ItemCondition.NEW, 35000, 2020, 120, 1.5));
            salon3.addProduct(new Vehicle("Skoda", "Octavia", ItemCondition.DAMAGED, 15000, 2017, 105, 1.4));
            salon3.addProduct(new Vehicle("Alfa Romeo", "Giulia", ItemCondition.NEW, 100000, 2021, 200, 2.9));
            salon3.addProduct(new Vehicle("Tesla", "Model 3", ItemCondition.NEW, 160000, 2022, 0, 0)); // Electric
        }

        System.out.println("Dodano " + salon1.getVehicleList().size() + " pojazdów do salonu Kraków.");
        System.out.println("Dodano " + salon2.getVehicleList().size() + " pojazdów do salonu Warszawa.");
        System.out.println("Dodano " + salon3.getVehicleList().size() + " pojazdów do salonu Wrocław.");
    }

    private void setupSalonComboBox()
    {
        salonComboBox.getItems().clear();
        salonComboBox.getItems().add("All Salons");
        salonComboBox.getItems().addAll(container.getSaloons().keySet());
        salonComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            System.out.println("Wybrano salon: " + newVal);
            updateVehicleTable(newVal);
        });
        if (!salonComboBox.getItems().isEmpty())
        {
            salonComboBox.getSelectionModel().selectFirst();
        }
        salonComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateVehicleView());
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

    private void updateVehicleTable(String salonName)
    {
        String selectedCondition = conditionComboBox.getSelectionModel().getSelectedItem();

        vehicleData.clear();

        if ("All Salons".equals(salonName))
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

    private Stream<Vehicle> getVehicleStream(String salonName, String condition)
    {
        Stream<Vehicle> stream;

        if ("All Salons".equals(salonName))
        {
            stream = container.getSaloons().values().stream()
                    .flatMap(salon -> salon.getVehicleList().stream());
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

    private String getSalonPhoneNumber(String salonName)
    {
        return "123-456-789";
    }

    private String getSalonLocation(String salonName)
    {
        return "City Center";
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

    private void filterVehicles(String searchText)
    {
        String selectedSalon = salonComboBox.getSelectionModel().getSelectedItem();
        String selectedCondition = conditionComboBox.getSelectionModel().getSelectedItem();

        String lowerCaseSearchText = searchText.toLowerCase().trim();

        ObservableList<Vehicle> filteredData = FXCollections.observableArrayList();

        if ("All Salons".equals(selectedSalon))
        {
            container.getSaloons().values().forEach(salon ->
                    filteredData.addAll(filterList(salon.getVehicleList(), lowerCaseSearchText, selectedCondition))
            );
        }
        else
        {
            CarShowRoom salon = container.getSaloons().get(selectedSalon);
            if (salon != null)
            {
                filteredData.addAll(filterList(salon.getVehicleList(), lowerCaseSearchText, selectedCondition));
            }
        }

        vehicleTable.setItems(filteredData);
    }

    private List<Vehicle> filterList(List<Vehicle> vehicles, String searchText, String selectedCondition)
    {
        Stream<Vehicle> stream = vehicles.stream();

        if (!"All Conditions".equals(selectedCondition))
        {
            stream = stream.filter(v -> v.getCondition().toString().equals(selectedCondition));
        }

        if (!searchText.isEmpty())
        {
            stream = stream.filter(v -> v.getBrand().toLowerCase().contains(searchText) ||
                    v.getModel().toLowerCase().contains(searchText));
        }

        return stream.collect(Collectors.toList());
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
        return "Unknown showroom.";
    }

    private String createTooltipText(Vehicle vehicle, String salonName)
    {
        return "Car showroom: " + salonName + "\n" +
                "Mileage: " + vehicle.getMileage() + " km\n" +
                "Capacity: " + vehicle.getEngineCapacity() + " L";
    }

    @FXML
    private void handleBuyAction()
    {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null && selectedVehicle.getQuantity() > 0)
        {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Purchase Vehicle");
            dialog.setHeaderText("Enter your details to reservation the " + selectedVehicle.getBrand() + " " + selectedVehicle.getModel());
            dialog.setContentText("Please enter your name:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name ->
            {
                selectedVehicle.setQuantity(selectedVehicle.getQuantity() - 1);
                updateVehicleTable(salonComboBox.getSelectionModel().getSelectedItem());
                vehicleTable.refresh();
                updateDetails(selectedVehicle);
                showAlert(Alert.AlertType.INFORMATION, "Purchase booking successful", "Thank you " + name + ", your booking has been confirmed! Remaining quantity: " + selectedVehicle.getQuantity());
            });
        }
        else
        {
            showAlert(Alert.AlertType.WARNING, "No Selection or No Stock", "Please select a vehicle to purchase or vehicle is out of stock.");
        }
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