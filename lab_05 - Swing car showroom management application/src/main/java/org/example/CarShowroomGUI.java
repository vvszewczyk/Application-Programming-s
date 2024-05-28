package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarShowroomGUI extends JFrame
{
    // Attributes
    private CarShowroomContainer container;
    private JTable table;
    private GenericTableModel<Vehicle> vehicleModel;
    private JComboBox<String> salonComboBox;

    // Constructor
    public CarShowroomGUI()
    {
        setTitle("Car Showroom Manager");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        container = new CarShowroomContainer();
        addTestSalonsToContainer();
        initialize();

        if (salonComboBox.getItemCount() > 0)
        {
            salonComboBox.setSelectedIndex(0);
            String selectedSalon = salonComboBox.getSelectedItem().toString();
            updateVehicleTable(selectedSalon);
        }
    }


    private void initialize()
    {
        setLayout(new BorderLayout());

        // Panel dla filtrów, wyboru stanu i wyboru salonów
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        // Pole tekstowe dla filtracji pojazdów po nazwie
        JTextField filterTextField = new JTextField(20);
        filterTextField.addActionListener(e -> filterVehiclesByName(filterTextField.getText()));
        filterPanel.add(filterTextField);

        // ComboBox dla filtracji pojazdów po stanie
        JComboBox<ItemCondition> stateComboBox = new JComboBox<>(ItemCondition.values());
        stateComboBox.addActionListener(e -> filterVehiclesByState((ItemCondition) stateComboBox.getSelectedItem()));
        filterPanel.add(stateComboBox);

        // ComboBox dla wyboru salonów
        salonComboBox = new JComboBox<>();
        updateSalonComboBox();
        salonComboBox.addActionListener(e ->
        {
            String selectedSalon = (String) salonComboBox.getSelectedItem();
            updateVehicleTable(selectedSalon);
        });
        filterPanel.add(salonComboBox);

        add(filterPanel, BorderLayout.PAGE_START);

        initializeTableModel();
        table = new JTable(vehicleModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel z przyciskami
        JPanel buttonPanel = new JPanel();
        JButton addSalonButton = new JButton("Add Salon");
        JButton removeSalonButton = new JButton("Remove Salon");
        JButton addVehicleButton = new JButton("Add Vehicle");
        JButton removeVehicleButton = new JButton("Remove Vehicle");
        JButton sortCentersButton = new JButton("Sort Centers by Current Load");
        sortCentersButton.addActionListener(e -> sortCentersByLoad());

        buttonPanel.add(addSalonButton);
        buttonPanel.add(removeSalonButton);
        buttonPanel.add(addVehicleButton);
        buttonPanel.add(removeVehicleButton);
        buttonPanel.add(sortCentersButton);

        addSalonButton.addActionListener(this::addSalon);
        removeSalonButton.addActionListener(this::removeSalon);
        addVehicleButton.addActionListener(this::addVehicle);
        removeVehicleButton.addActionListener(this::removeVehicle);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initializeTableModel()
    {
        GenericTableModel.DataAdapter<Vehicle> vehicleDataAdapter = new GenericTableModel.DataAdapter<>()
        {
            @Override
            public String[] getColumnNames()
            {
                return new String[]{"Brand", "Model", "Year", "Condition", "Price", "Engine Capacity", "Mileage"};
            }

            @Override
            public Object getValueAt(Vehicle item, int columnIndex)
            {
                switch (columnIndex)
                {
                    case 0:
                        return item.getBrand();
                    case 1:
                        return item.getModel();
                    case 2:
                        return item.getYearOfProduction();
                    case 3:
                        return item.getCondition().toString();
                    case 4:
                        return item.getPrice();
                    case 5:
                        return item.getEngineCapacity();
                    case 6:
                        return item.getMileage();
                    default:
                        throw new IllegalArgumentException("Invalid column index");
                }
            }

        };
        vehicleModel = new GenericTableModel<>(vehicleDataAdapter);
        table = new JTable(vehicleModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }



    // Loading data to application
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
            salon1.addProduct(new Vehicle("Skoda", "Fabia", ItemCondition.DAMAGED, 8000, 2014, 60, 1.2));
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
            salon3.addProduct(new Vehicle("Mercedes-Benz", "C-Class", ItemCondition.NEW, 70000, 2022, 160, 2.0));
            salon3.addProduct(new Vehicle("Lexus", "IS", ItemCondition.USED, 65000, 2018, 130, 2.5));
            salon3.addProduct(new Vehicle("Honda", "Civic", ItemCondition.NEW, 35000, 2020, 120, 1.5));
            salon3.addProduct(new Vehicle("Skoda", "Octavia", ItemCondition.DAMAGED, 15000, 2017, 105, 1.4));
            salon3.addProduct(new Vehicle("Ford", "Fiesta", ItemCondition.NEW, 30000, 2019, 95, 1.0));
            salon3.addProduct(new Vehicle("Alfa Romeo", "Giulia", ItemCondition.NEW, 100000, 2021, 200, 2.9));
            salon3.addProduct(new Vehicle("Dodge", "Charger", ItemCondition.USED, 75000, 2019, 292, 3.6));
            salon3.addProduct(new Vehicle("Tesla", "Model 3", ItemCondition.NEW, 160000, 2022, 0, 0)); // Electric
            salon3.addProduct(new Vehicle("Fiat", "Punto", ItemCondition.DAMAGED, 4000, 2010, 77, 1.4));
            salon3.addProduct(new Vehicle("Nissan", "Micra", ItemCondition.USED, 14000, 2016, 80, 1.2));

        }
    }


    // Sorting and filtering methods
    private void sortCentersByLoad()
    {
        List<String> sortedSalons = container.getSortedSaloonsByLoad();
        salonComboBox.removeAllItems();
        for (String salonName : sortedSalons)
        {
            salonComboBox.addItem(salonName);
        }
    }

    private void filterVehiclesByName(String filterText)
    {
        String selectedSalon = (String) salonComboBox.getSelectedItem();
        CarShowRoom salon = container.getSaloons().get(selectedSalon);
        if (salon != null)
        {
            List<Vehicle> filteredVehicles = salon.getVehicleList().stream()
                    .filter(v -> v.getBrand().contains(filterText) || v.getModel().contains(filterText))
                    .collect(Collectors.toList());
            vehicleModel.setDataList(filteredVehicles);
        }
    }

    private void filterVehiclesByState(ItemCondition condition)
    {
        String selectedSalon = (String) salonComboBox.getSelectedItem();
        CarShowRoom salon = container.getSaloons().get(selectedSalon);
        if (salon != null)
        {
            List<Vehicle> filteredVehicles = salon.getVehicleList().stream()
                    .filter(v -> v.getCondition() == condition)
                    .collect(Collectors.toList());
            vehicleModel.setDataList(filteredVehicles);
        }
    }

    

    // Update methods
    private void updateSalonComboBox()
    {
        salonComboBox.removeAllItems();
        for (String salonName : container.getSaloons().keySet())
        {
            salonComboBox.addItem(salonName);
        }
    }

    private void updateVehicleTable(String salonName)
    {
        if (salonName != null)
        {
            CarShowRoom salon = container.getSaloons().get(salonName);
            if (salon != null)
            {
                List<Vehicle> vehicles = salon.getVehicleList();
                vehicleModel.setDataList(vehicles);
            }
            else
            {
                vehicleModel.setDataList(new ArrayList<>());
            }
        }
    }


    // Button operations
    private void addSalon(ActionEvent e)
    {
        String salonName = JOptionPane.showInputDialog(this, "Enter salon name:");
        if (salonName != null && !salonName.trim().isEmpty())
        {
            if (!container.getSaloons().containsKey(salonName))
            {
                container.addCenter(salonName, 10);
                updateSalonComboBox();
                salonComboBox.setSelectedItem(salonName);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Salon already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void removeSalon(ActionEvent e)
    {
        String selectedSalon = (String) salonComboBox.getSelectedItem();
        if (selectedSalon != null)
        {
            container.removeCenter(selectedSalon);
            updateSalonComboBox();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please select a salon to remove.");
        }
    }

    private void addVehicle(ActionEvent e)
    {
        String selectedSalon = (String) salonComboBox.getSelectedItem();
        if (selectedSalon == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a salon first.");
            return;
        }

        CarShowRoom salon = container.getSaloons().get(selectedSalon);
        if (salon == null)
        {
            JOptionPane.showMessageDialog(this, "Selected salon is not found.");
            return;
        }

        String vehicleData = JOptionPane.showInputDialog(this, "Enter vehicle data (Brand;Model;Year;Condition;Price;EngineCapacity;Mileage):");
        if (vehicleData != null && !vehicleData.trim().isEmpty())
        {
            String[] dataParts = vehicleData.split(";");
            if (dataParts.length != 7)
            {
                JOptionPane.showMessageDialog(this, "Incorrect data format. Please use the format: Brand;Model;Year;Condition");
                return;
            }

            try
            {
                String brand = dataParts[0].trim();
                String model = dataParts[1].trim();
                int year = Integer.parseInt(dataParts[2].trim());
                ItemCondition condition = ItemCondition.valueOf(dataParts[3].trim().toUpperCase());
                double price = Double.parseDouble(dataParts[4].trim());
                double engineCapacity = Double.parseDouble(dataParts[5].trim());
                double mileage = Double.parseDouble(dataParts[6].trim());

                Vehicle newVehicle = new Vehicle(brand, model, condition, price, year, mileage, engineCapacity);
                if (salon.addProduct(newVehicle))
                {
                    JOptionPane.showMessageDialog(this, "Vehicle added to salon " + selectedSalon + ".");
                    List<Vehicle> updatedVehicles = salon.getVehicleList();
                    vehicleModel.setDataList(updatedVehicles);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Failed to add vehicle. The salon might be full.");
                }
            }
            catch (IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(this, "Invalid data. Please check the year and condition format.");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Vehicle addition cancelled.");
        }
    }


    private void removeVehicle(ActionEvent e)
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0)
        {
            String selectedSalon = (String) salonComboBox.getSelectedItem();
            CarShowRoom salon = container.getSaloons().get(selectedSalon);
            if (salon != null && !salon.getVehicleList().isEmpty())
            {
                Vehicle vehicleToRemove = salon.getVehicleList().get(selectedRow);
                salon.removeProduct(vehicleToRemove);
                JOptionPane.showMessageDialog(this, "Vehicle removed from salon " + selectedSalon + ".");
                List<Vehicle> updatedVehicles = salon.getVehicleList();
                vehicleModel.setDataList(updatedVehicles);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please select a vehicle to remove.");
        }

    }
}
