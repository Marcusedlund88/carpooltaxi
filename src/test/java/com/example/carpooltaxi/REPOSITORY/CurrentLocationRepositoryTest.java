package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CurrentLocationRepositoryTest {

    @Autowired
    private CurrentLocationRepository currentLocationRepository;

    @Test
    void getLocationByIdAssertNotNull(){
        assertNotNull(currentLocationRepository.getLocationById(1));
    }

    @Test
    void getLocationByUserIdAssertNotNull() {

        assertNotNull(currentLocationRepository.findAll());
    }

    @Test
    void getLocationByUserIdAssertSizeOne(){
        List<Location> locations = currentLocationRepository.getLocationByAppUser_Id(1);
        assertEquals(locations.size(), 1);
    }
    @Test
    void getLocationByUserIdAssertEqual(){

        List<Location> locations = currentLocationRepository.getLocationByAppUser_Id(4);
        assertEquals(locations.get(0).getId(),4);
    }
}
