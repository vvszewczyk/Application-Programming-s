package org.pau.springbootapplication.MainSoruce;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating")
public class Rating implements Serializable
{
    // Attributes and annotations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rating", nullable = false)
    @NotNull
    @Min(0)
    @Max(5)
    private int rating;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    @NotNull
    private Vehicle vehicle;

    @Column(name = "date", nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;


    // Constructors
    public Rating(int rating, Vehicle vehicle, String comment)
    {
        checkRating(rating);
        this.rating = rating;
        this.vehicle = vehicle;
        this.comment = comment;
        this.dateTime = LocalDateTime.now();
    }

    public Rating() {}


    // Getters and Setters
    public Long getId()
    {
        return id;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    // Methods
    private void checkRating(int rating)
    {
        if (rating < 0 || rating > 5)
        {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
    }
}
