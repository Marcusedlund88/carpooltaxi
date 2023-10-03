package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreviousLocationRepository extends JpaRepository<Location, Long> {
    Location getLocationById(long id);
    List<Location> getLocationByAppUser_Id(long id);
}
