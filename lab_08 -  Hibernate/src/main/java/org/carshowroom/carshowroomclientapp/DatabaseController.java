package org.carshowroom.carshowroomclientapp;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseController
{
    // Attribute
    private static final SessionFactory sessionFactory = buildSessionFactory();


    // Getter
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    // Methods
    private static SessionFactory buildSessionFactory()
    {
        try
        {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static List<CarShowRoom> loadDatabase()
    {
        try (Session session = getSessionFactory().openSession())
        {
            String hql = "FROM CarShowRoom";
            Query<CarShowRoom> query = session.createQuery(hql, CarShowRoom.class);
            List<CarShowRoom> results = query.list();
            System.out.println("Loaded " + results.size() + " showrooms");
            return results;
        }
        catch (Exception e)
        {
            System.err.println("Couldn't create a session: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static List<Vehicle> loadFavoritesFromDatabase()
    {
        try (Session session = getSessionFactory().openSession())
        {
            String hql = "FROM Vehicle v WHERE v.carShowRoom.showroom = :salonName";
            Query<Vehicle> query = session.createQuery(hql, Vehicle.class);
            query.setParameter("salonName", "Favorites");
            return query.list();
        }
        catch (Exception e)
        {
            System.err.println("Error loading favorites from database: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static void loadRatingsForVehicle(Vehicle vehicle)
    {
        try (Session session = getSessionFactory().openSession())
        {
            String hql = "FROM Rating r WHERE r.vehicle.id = :vehicleId";
            Query<Rating> query = session.createQuery(hql, Rating.class);
            query.setParameter("vehicleId", vehicle.getId());
            List<Rating> ratings = query.list();
            vehicle.setRatings(ratings);
        }
        catch (Exception e)
        {
            System.err.println("Error loading ratings for vehicle: " + e.getMessage());
        }
    }

    public static void addVehicleToFavorites(Vehicle vehicle)
    {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            String hql = "FROM CarShowRoom WHERE showroom = :salonName";
            Query<CarShowRoom> query = session.createQuery(hql, CarShowRoom.class);
            query.setParameter("salonName", "Favorites");
            CarShowRoom favoriteSalon = query.uniqueResult();

            if (favoriteSalon == null)
            {
                favoriteSalon = new CarShowRoom("Favorites", new ArrayList<>(), 100);
                session.save(favoriteSalon);
            }

            Vehicle newVehicle = new Vehicle(vehicle);
            newVehicle.setCarShowRoom(favoriteSalon);
            favoriteSalon.getVehicleList().add(newVehicle);
            session.save(newVehicle);

            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            System.err.println("Error adding vehicle to favorites: " + e.getMessage());
        }
    }

    public static boolean isVehicleInFavorites(Vehicle vehicle)
    {
        try (Session session = getSessionFactory().openSession())
        {
            String hql = "SELECT COUNT(v) FROM Vehicle v WHERE v.carShowRoom.showroom = :salonName AND v.brand = :brand AND v.model = :model AND v.yearOfProduction = :year AND v.condition = :condition";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("salonName", "Favorites");
            query.setParameter("brand", vehicle.getBrand());
            query.setParameter("model", vehicle.getModel());
            query.setParameter("year", vehicle.getYearOfProduction());
            query.setParameter("condition", vehicle.getCondition());

            return query.uniqueResult() > 0;
        }
        catch (Exception e)
        {
            System.err.println("Error checking if vehicle is in favorites: " + e.getMessage());
            return false;
        }
    }

    public static void removeFromFavorites(Vehicle data)
    {
        try (Session session = getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.remove(data);
            transaction.commit();
        }
        catch (RuntimeException e)
        {
            System.err.println("Error removing vehicle from favorites: " + e.getMessage());
        }
    }

    public static void updateVehicleQuantity(Vehicle vehicle)
    {
        try (Session session = getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE Vehicle SET quantity = :quantity WHERE id = :id");
            query.setParameter("quantity", vehicle.getQuantity());
            query.setParameter("id", vehicle.getId());
            query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e)
        {
            System.err.println("Error updating vehicle quantity: " + e.getMessage());
        }
    }

    public static void saveRating(Rating rating)
    {
        try (Session session = getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.save(rating);
            transaction.commit();
        }
        catch (Exception e)
        {
            System.err.println("Error saving rating: " + e.getMessage());
        }
    }

    public static List<Vehicle> filterVehicles(String searchText, String condition)
    {
        try (Session session = getSessionFactory().openSession())
        {
            StringBuilder hql = new StringBuilder("FROM Vehicle v WHERE 1=1");

            if (searchText != null && !searchText.isEmpty())
            {
                hql.append(" AND (lower(v.brand) LIKE :searchText OR lower(v.model) LIKE :searchText)");
            }

            if (condition != null && !condition.equals("All Conditions"))
            {
                hql.append(" AND v.condition = :condition");
            }

            Query<Vehicle> query = session.createQuery(hql.toString(), Vehicle.class);

            if (searchText != null && !searchText.isEmpty())
            {
                query.setParameter("searchText", "%" + searchText.toLowerCase() + "%");
            }

            if (condition != null && !condition.equals("All Conditions"))
            {
                query.setParameter("condition", ItemCondition.valueOf(condition));
            }

            return query.list();
        }
        catch (Exception e)
        {
            System.err.println("Error filtering vehicles: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    static void saveToCSV()
    {
        exportToCSV(CarShowRoom.class, "carshowroom.csv");
        exportToCSV(Vehicle.class, "vehicle.csv");
        exportToCSV(Rating.class, "rating.csv");
    }

    public static <T> void exportToCSV(Class<T> entityClass, String filename)
    {
        try (Session session = sessionFactory.openSession(); FileWriter writer = new FileWriter(filename))
        {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);
            cq.select(root);

            Query<T> query = session.createQuery(cq);
            List<T> results = query.getResultList();

            String header = String.join(", ", DatabaseReflection.getFieldNames(entityClass)) + "\n";
            writer.append(header);

            for (T result : results)
            {
                writer.append(DatabaseReflection.convertToCsvString(result));
                writer.append("\n");
            }
        }
        catch (IOException e)
        {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
