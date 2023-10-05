package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.CurrentLocation;
import com.example.carpooltaxi.DATA.PreviousLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreviousLocationRepository extends JpaRepository<PreviousLocation, Long> {
    PreviousLocation getLocationById(long id);
    List<PreviousLocation> getLocationByAppUser_Id(long id);
}
