package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.CurrentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface CurrentLocationRepository extends JpaRepository<CurrentLocation, Long> {
        CurrentLocation getLocationById(long id);
        List<CurrentLocation> getLocationByAppUser_Id(long id);
    }
