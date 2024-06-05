package org.carshowroom.carshowroomclientapp;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseReflection
{
    private static final String RATING_COUNT_FIELD = "ratingCount";
    private static final String AVERAGE_RATING_FIELD = "averageRating";
    private static final Logger LOGGER = Logger.getLogger(DatabaseReflection.class.getName());

    public static List<String> getFieldNames(Class<?> c)
    {
        return Arrays.stream(c.getDeclaredFields())
                .map(Field::getName)
                .filter(fieldName -> !fieldName.equals(RATING_COUNT_FIELD) && !fieldName.equals(AVERAGE_RATING_FIELD))
                .collect(Collectors.toList());
    }

    public static String convertToCsvString(Object obj)
    {
        Field[] fields = obj.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> !field.getName().equals(RATING_COUNT_FIELD) && !field.getName().equals(AVERAGE_RATING_FIELD))
                .map(field -> getFieldValue(obj, field))
                .collect(Collectors.joining(", "));
    }

    private static String getFieldValue(Object obj, Field field)
    {
        field.setAccessible(true);
        try
        {
            Object value = field.get(obj);
            if (value instanceof List)
            {
                return ((List<?>) value).stream()
                        .map(DatabaseReflection::getRatingId)
                        .collect(Collectors.joining(","));
            }
            else if (isInstanceOfClass(value, "CarShowRoom") || isInstanceOfClass(value, "Vehicle"))
            {
                return getFieldIdValue(value);
            }
            else
            {
                return value != null ? value.toString() : "";
            }
        }
        catch (IllegalAccessException e)
        {
            LOGGER.log(Level.SEVERE, "Unable to access field value", e);
            return "ERROR";
        }
    }

    private static String getRatingId(Object rating)
    {
        try
        {
            Field idField = rating.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return idField.get(rating).toString();
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            LOGGER.log(Level.SEVERE, "Unable to access rating ID", e);
            return "ERROR";
        }
    }

    private static boolean isInstanceOfClass(Object obj, String className)
    {
        return obj != null && obj.getClass().getSimpleName().equals(className);
    }

    private static String getFieldIdValue(Object obj)
    {
        try
        {
            Field idField = obj.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return idField.get(obj).toString();
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            LOGGER.log(Level.SEVERE, "Unable to access ID field", e);
            return "ERROR";
        }
    }
}
