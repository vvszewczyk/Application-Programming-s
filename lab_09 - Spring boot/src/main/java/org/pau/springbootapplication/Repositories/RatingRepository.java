package org.pau.springbootapplication.Repositories;

import org.pau.springbootapplication.MainSoruce.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>
{
    List<Rating> findByVehicleId(Long vehicleId);
}
