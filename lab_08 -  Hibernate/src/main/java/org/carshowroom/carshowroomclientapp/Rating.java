package org.carshowroom.carshowroomclientapp;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating")
public class Rating implements Serializable
{
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(name = "date", nullable = false)
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

    protected Rating()
    {

    }

    // Methods
    private void checkRating(int rating)
    {
        if (rating < 0 || rating > 5)
        {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
    }

    public int getRating()
    {
        return rating;
    }

}
