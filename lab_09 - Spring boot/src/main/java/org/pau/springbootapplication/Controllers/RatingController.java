package org.pau.springbootapplication.Controllers;

import jakarta.validation.Valid;
import org.pau.springbootapplication.MainSoruce.Rating;
import org.pau.springbootapplication.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // Handle rating events
    @PostMapping
    public ResponseEntity<Void> addRating(@Valid @RequestBody Rating rating)
    {
        ratingService.addRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
