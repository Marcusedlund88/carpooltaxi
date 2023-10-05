package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.CurrentLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CurrentCurrentLocationRepositoryTest {

    @Mock
    private CurrentLocationRepository currentLocationRepository;

    @InjectMocks
    private CurrentLocation currentLocation;

    @InjectMocks
    private AppUser appUser;

    @BeforeEach
    public void insertValueToMockDb(){
        MockitoAnnotations.openMocks(this);

        appUser = new AppUser(9, "dummyOne");
        currentLocation = new CurrentLocation(9, appUser, "dummyLong", "dummyLat");

        when(currentLocationRepository.save(currentLocation)).thenReturn(currentLocation);
        when(currentLocationRepository.findAll()).thenReturn(List.of(currentLocation));
        when(currentLocationRepository.getLocationByAppUser_Id(appUser.getId())).thenReturn(List.of(currentLocation));
        when(currentLocationRepository.getLocationById(currentLocation.getId())).thenReturn(currentLocation);

    }
    @Test
    void getLocationByIdAssertNotNull(){
        assertNotNull(currentLocationRepository.getLocationById(9));
    }

    @Test
    void getLocationByUserIdAssertNull() {

        assertNotNull(currentLocationRepository.getLocationByAppUser_Id(1));
    }
    @Test
    void getLocationByUserIdAssertEqual(){

        List<CurrentLocation> currentLocations = currentLocationRepository.getLocationByAppUser_Id(9);
        assertEquals(currentLocations.get(0).getId(),9);
    }
    @Test
    void getLocationByUserIdAssertSizeOne(){
        List<CurrentLocation> currentLocations = currentLocationRepository.getLocationByAppUser_Id(9);
        assertEquals(currentLocations.size(), 1);
    }

}

