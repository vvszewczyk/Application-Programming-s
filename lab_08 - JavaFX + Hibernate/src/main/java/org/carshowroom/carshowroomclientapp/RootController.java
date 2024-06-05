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
import static org.carshowroom.carshowroomclientapp.DatabaseController.isVehicleInFavorites;

public class RootController
{
    // Attributes
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
    private TableColumn<Vehicle, Long> ratingCountColumn;
    @FXML
    private TableColumn<Vehicle, Double> averageRatingColumn;
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

    private final ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();
    private static CarShowroomContainer container;


    // Initialize and setup assets
    public void initialize()
    {
        try
        {
            Image logoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/carshowroom/carshowroomclientapp/logo.png")));
            logoImageView.setImage(logoImage);
            initializeData();
            loadFavorites();
            setupSalonComboBox();
            setupConditionComboBox();
            initializeTableColumns();
            setupFilterTextField();
            setupTableSelection();
            updateVehicleTable(salonComboBox.getSelectionModel().getSelectedItem());
        }
        catch (Exception e)
        {
            System.err.println("Error during initialization: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Initialization Error", "Error during initialization: " + e.getMessage());
        }
    }

    private void initializeData()
    {
        List<CarShowRoom> showrooms = DatabaseController.loadDatabase();
        container = new CarShowroomContainer();
        boolean hasFavorites = false;
        for (CarShowRoom showroom : showrooms)
        {
            container.addShowroom(showroom);
            vehicleData.addAll(showroom.getVehicleList());
            if (showroom.getName().equals("Favorites"))
            {
                hasFavorites = true;
            }
        }
        if (!hasFavorites)
        {
            CarShowRoom favoriteSalon = new CarShowRoom();
            favoriteSalon.setName("Favorites");
            favoriteSalon.setVehicleList(new ArrayList<>());
            container.addShowroom(favoriteSalon);
        }
        System.out.println("Total vehicles added: " + vehicleData.size());
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
        ratingCountColumn.setCellValueFactory(new PropertyValueFactory<>("ratingCount"));
        averageRatingColumn.setCellValueFactory(new PropertyValueFactory<>("averageRating"));

        vehicleTable.setRowFactory(tv -> new TableRow<Vehicle>()
        {
            private final Tooltip tooltip = new Tooltip();

            @Override
            public void updateItem(Vehicle vehicle, boolean empty)
            {
                super.updateItem(vehicle, empty);
                if (vehicle == null || empty)
                {
                    setTooltip(null);
                }
                else
                {
                    String salonName = findSalonNameForVehicle(vehicle);
                    tooltip.setText(createTooltipText(vehicle, salonName));
                    setTooltip(tooltip);
                }
            }
        });
    }

    private void setupSalonComboBox()
    {
        salonComboBox.getItems().clear();
        salonComboBox.getItems().add("All Salons");

        Set<String> addedSalons = new HashSet<>();
        for (String salonName : container.getSaloons().keySet())
        {
            if (!addedSalons.contains(salonName))
            {
                salonComboBox.getItems().add(salonName);
                addedSalons.add(salonName);
            }
        }

        if (!addedSalons.contains("Favorites"))
        {
            salonComboBox.getItems().add("Favorites");
            addedSalons.add("Favorites");
        }

        salonComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            if ("Favorites".equals(newVal))
            {
                conditionComboBox.getSelectionModel().select("All Conditions");
                loadFavorites();
                updateVehicleTable(newVal);
            }
            else
            {
                System.out.println("Showroom has been chosen: " + newVal);
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

    private void setupFilterTextField()
    {
        filterTextField.textProperty().addListener((observable, oldValue, newValue) ->
        {
            updateVehicleView();
        });

        conditionComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            updateVehicleView();
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


    // Update methods
    private void updateVehicleTable(String salonName)
    {
        vehicleData.clear();

        if ("Favorites".equals(salonName))
        {
            List<Vehicle> favorites = loadFavorites();
            for (Vehicle vehicle : favorites)
            {
                DatabaseController.loadRatingsForVehicle(vehicle);
            }
            vehicleData.setAll(favorites);
        }
        else if ("All Salons".equals(salonName))
        {
            for (CarShowRoom salon : container.getSaloons().values())
            {
                for (Vehicle vehicle : salon.getVehicleList())
                {
                    DatabaseController.loadRatingsForVehicle(vehicle);
                    vehicleData.add(vehicle);
                }
            }
        }
        else
        {
            CarShowRoom salon = container.getSaloons().get(salonName);
            if (salon != null)
            {
                for (Vehicle vehicle : salon.getVehicleList())
                {
                    DatabaseController.loadRatingsForVehicle(vehicle);
                    vehicleData.add(vehicle);
                }
            }
        }

        vehicleTable.setItems(vehicleData);
        vehicleTable.refresh();
        System.out.println("Table has been updated for showroom: " + salonName + ", number of vehicles: " + vehicleData.size());
    }

    private void updateVehicleView()
    {
        String searchText = filterTextField.getText();
        String selectedCondition = conditionComboBox.getSelectionModel().getSelectedItem();
        String selectedSalon = salonComboBox.getSelectionModel().getSelectedItem();

        List<Vehicle> vehicles = DatabaseController.filterVehicles(searchText, selectedCondition);

        if ("Favorites".equals(selectedSalon))
        {
            vehicles = vehicles.stream()
                    .filter(DatabaseController::isVehicleInFavorites)
                    .collect(Collectors.toList());
        }
        else if (!"All Salons".equals(selectedSalon))
        {
            vehicles = vehicles.stream()
                    .filter(v -> selectedSalon.equals(findSalonNameForVehicle(v)))
                    .collect(Collectors.toList());
        }

        vehicleTable.setItems(FXCollections.observableArrayList(vehicles));
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

    private void updateVehicleImage(Vehicle vehicle)
    {
        String imageName = vehicle.getBrand().toLowerCase() + "_" + vehicle.getModel().toLowerCase() + ".png";
        String imagePath = "/org/carshowroom/carshowroomclientapp/images/" + imageName;
        System.out.println("Loading image from: " + imagePath);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        if (image.isError())
        {
            System.out.println("Error during loading image.");
        }
        else
        {
            vehicleImageView.setImage(image);
            System.out.println("Image has been loaded successfully.");
        }
    }

    // Example getters
    private String getSalonPhoneNumber(String salonName)
    {
        return "123-456-789";
    }


    private String getSalonLocation(String salonName)
    {
        return "City Center";
    }


    // Rest of methods
    private List<Vehicle> loadFavorites()
    {
        List<Vehicle> favorites = DatabaseController.loadFavoritesFromDatabase();

        CarShowRoom favoritesSalon = container.getSaloon("Favorites");
        if (favoritesSalon != null)
        {
            favoritesSalon.setVehicleList(favorites);
            vehicleData.setAll(favorites);
            vehicleTable.refresh();
        }
        return favorites;
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
        return "Unknown showroom";
    }

    private String createTooltipText(Vehicle vehicle, String salonName)
    {
        return "Car showroom: " + salonName + "\n" +
                "Mileage: " + vehicle.getMileage() + " km\n" +
                "Capacity: " + vehicle.getEngineCapacity() + " L";
    }

    // Buttons methods
    @FXML
    private void handleBuyAction()
    {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null)
        {
            if (selectedVehicle.getQuantity() > 0)
            {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Purchase Vehicle");
                dialog.setHeaderText("Enter your details to reserve the " + selectedVehicle.getBrand() + " " + selectedVehicle.getModel());
                dialog.setContentText("Please enter your name:");
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name ->
                {
                    selectedVehicle.setQuantity(selectedVehicle.getQuantity() - 1);
                    DatabaseController.updateVehicleQuantity(selectedVehicle);

                    if ("Favorites".equals(salonComboBox.getSelectionModel().getSelectedItem()))
                    {
                        updateFavoritesFile();
                    }

                    updateVehicleTable(salonComboBox.getSelectionModel().getSelectedItem());
                    vehicleTable.refresh();
                    updateDetails(selectedVehicle);
                    saveData();
                    showAlert(Alert.AlertType.INFORMATION, "Purchase booking successful", "Thank you " + name + ", your booking has been confirmed! Remaining quantity: " + selectedVehicle.getQuantity());
                });
            }
            else
            {
                showAlert(Alert.AlertType.WARNING, "No Stock", "The selected vehicle is out of stock.");
            }
        }
        else
        {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a vehicle to purchase.");
        }
    }
    @FXML
    private void handleRateAction()
    {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null)
        {
            Dialog<Rating> dialog = new Dialog<>();
            dialog.setTitle("Rate Vehicle");

            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.setHeaderText("Rate " + selectedVehicle.getBrand() + " " + selectedVehicle.getModel());

            Label ratingLabel = new Label("Rating (0-5):");
            TextField ratingField = new TextField();
            Label commentLabel = new Label("Comment:");
            TextArea commentArea = new TextArea();

            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            dialogPane.setContent(new VBox(8, ratingLabel, ratingField, commentLabel, commentArea));

            dialog.setResultConverter(button ->
            {
                if (button == ButtonType.OK)
                {
                    try
                    {
                        int ratingValue = Integer.parseInt(ratingField.getText());
                        if (ratingValue < 0 || ratingValue > 5)
                        {
                            throw new NumberFormatException();
                        }
                        String comment = commentArea.getText();
                        return new Rating(ratingValue, selectedVehicle, comment);
                    }
                    catch (NumberFormatException e)
                    {
                        showAlert(Alert.AlertType.ERROR, "Invalid Input", "Rating must be a number between 0 and 5.");
                    }
                }
                return null;
            });

            Optional<Rating> result = dialog.showAndWait();
            result.ifPresent(rating ->
            {
                DatabaseController.saveRating(rating);
                DatabaseController.loadRatingsForVehicle(selectedVehicle);
                updateVehicleTable(salonComboBox.getSelectionModel().getSelectedItem());
                vehicleTable.refresh();
                saveData();
                showAlert(Alert.AlertType.INFORMATION, "Rating Added", "Thank you for rating!");
            });
        }
        else
        {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a vehicle to rate.");
        }
    }
    @FXML
    private void handleAddToFavorites()
    {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null)
        {
            if (isVehicleInFavorites(selectedVehicle))
            {
                showAlert(Alert.AlertType.WARNING, "Duplicate Entry", "The vehicle is already in your favorites.");
                return;
            }

            String currentSalon = salonComboBox.getSelectionModel().getSelectedItem();
            DatabaseController.addVehicleToFavorites(selectedVehicle);
            CarShowRoom favoritesSalon = container.getSaloon("Favorites");
            favoritesSalon.carList.add(selectedVehicle);

            if (currentSalon.equals("Favorites"))
            {
                loadFavorites();
            }
            updateVehicleTable(currentSalon);
            vehicleTable.refresh();
            salonComboBox.getSelectionModel().select(currentSalon);

            showAlert(Alert.AlertType.INFORMATION, "Added to Favorites", "The vehicle has been added to your favorites.");
        }
        else
        {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a vehicle to add to favorites.");
        }
    }
    @FXML
    private void handleRemoveFromFavorites()
    {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null)
        {
            String currentSalon = salonComboBox.getSelectionModel().getSelectedItem();
            DatabaseController.removeFromFavorites(selectedVehicle);

            if (currentSalon.equals("Favorites"))
            {
                CarShowRoom favoritesSalon = container.getSaloon("Favorites");
                favoritesSalon.getVehicleList().remove(selectedVehicle);
                loadFavorites();
            }

            updateVehicleTable(currentSalon);
            vehicleTable.refresh();
            salonComboBox.getSelectionModel().select(currentSalon);
            showAlert(Alert.AlertType.INFORMATION, "Removed from Favorites", "The vehicle has been removed from your favorites.");
        }
        else
        {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a vehicle to remove from favorites.");
        }
    }

    // Serialize data
    private void saveData()
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser")))
        {
            out.writeObject(container);
            DatabaseController.saveToCSV();
        }
        catch (IOException e)
        {
            showAlert(Alert.AlertType.ERROR, "Save Error", "Failed to save data.");
        }
    }

    private void saveFavorites(List<Vehicle> vehicles)
    {
        try (FileWriter writer = new FileWriter("favorites.csv"))
        {
            List<Field> fields = Arrays.stream(Vehicle.class.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(CsvColumn.class))
                    .sorted(Comparator.comparingInt(f -> f.getAnnotation(CsvColumn.class).order()))
                    .toList();

            String header = fields.stream()
                    .map(f -> f.getAnnotation(CsvColumn.class).name())
                    .collect(Collectors.joining(","));
            writer.append(header).append("\n");

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
                writer.append(line).append("\n");
            }
            writer.flush();
        }
        catch (IOException e)
        {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    // Alert
    private void showAlert(Alert.AlertType alertType, String title, String message)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}