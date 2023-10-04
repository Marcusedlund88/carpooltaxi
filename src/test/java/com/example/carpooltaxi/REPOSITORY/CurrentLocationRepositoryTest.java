package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.Location;
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
public class CurrentLocationRepositoryTest {

    @Mock
    private CurrentLocationRepository currentLocationRepository;

    @InjectMocks
    private Location location;

    @InjectMocks
    private AppUser appUser;

    @BeforeEach
    public void insertValueToMockDb(){
        MockitoAnnotations.openMocks(this);

        appUser = new AppUser(9, "dummyOne");
        location = new Location(9, appUser, "dummyLong", "dummyLat");

        when(currentLocationRepository.save(location)).thenReturn(location);
        when(currentLocationRepository.findAll()).thenReturn(List.of(location));
        when(currentLocationRepository.getLocationByAppUser_Id(appUser.getId())).thenReturn(List.of(location));
        when(currentLocationRepository.getLocationById(location.getId())).thenReturn(location);

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

        List<Location> locations = currentLocationRepository.getLocationByAppUser_Id(9);
        assertEquals(locations.get(0).getId(),9);
    }
    @Test
    void getLocationByUserIdAssertSizeOne(){
        List<Location> locations = currentLocationRepository.getLocationByAppUser_Id(9);
        assertEquals(locations.size(), 1);
    }

}

