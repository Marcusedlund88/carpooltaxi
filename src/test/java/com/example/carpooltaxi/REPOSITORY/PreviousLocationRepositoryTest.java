package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PreviousLocationRepositoryTest {


    @Autowired
    private PreviousLocationRepository previousLocationRepository;

    @Test
    void getLocationByIdAssertNotNull(){
        assertNotNull(previousLocationRepository.getLocationById(1));
    }

    @Test
    void getLocationByUserIdAssertNotNull() {

        assertNotNull(previousLocationRepository.findAll());
    }
    @Test
    void getLocationByUserIdAssertEqual(){

        List<Location> locations = previousLocationRepository.getLocationByAppUser_Id(2);
        assertEquals(locations.get(0).getId(),2);
    }
    @Test
    void getLocationByUserIdAssertSizeOne(){
        List<Location> locations = previousLocationRepository.getLocationByAppUser_Id(1);
        assertEquals(locations.size(), 1);
    }

}
