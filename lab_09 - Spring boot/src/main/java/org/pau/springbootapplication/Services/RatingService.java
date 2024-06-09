package org.pau.springbootapplication.Services;

import org.pau.springbootapplication.MainSoruce.Rating;
import org.pau.springbootapplication.Repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService
{
    @Autowired
    private RatingRepository ratingRepository;

    public void addRating(Rating rating)
    {
        ratingRepository.save(rating);
    }
}
