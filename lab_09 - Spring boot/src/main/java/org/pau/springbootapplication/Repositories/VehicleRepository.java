package org.pau.springbootapplication.Repositories;

import org.pau.springbootapplication.MainSoruce.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>
{}
