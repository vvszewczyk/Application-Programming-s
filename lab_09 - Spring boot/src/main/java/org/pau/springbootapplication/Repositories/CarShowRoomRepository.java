package org.pau.springbootapplication.Repositories;

import org.pau.springbootapplication.MainSoruce.CarShowRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarShowRoomRepository extends JpaRepository<CarShowRoom, Long>
{}
