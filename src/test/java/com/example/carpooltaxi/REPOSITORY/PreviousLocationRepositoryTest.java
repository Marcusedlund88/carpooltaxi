package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.Location;
import com.example.carpooltaxi.REPOSITORY.PreviousLocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PreviousLocationRepositoryTest {


    @Mock
    private PreviousLocationRepository previousLocationRepository;

    @InjectMocks
    private Location location;

    @InjectMocks
    private AppUser appUser;

    @BeforeEach
    public void insertValueToMockDb(){
        MockitoAnnotations.openMocks(this);

        appUser = new AppUser(9, "dummyOne");
        location = new Location(9, appUser, "dummyLong", "dummyLat");

        when(previousLocationRepository.save(location)).thenReturn(location);
        when(previousLocationRepository.findAll()).thenReturn(List.of(location));
        when(previousLocationRepository.getLocationByAppUser_Id(appUser.getId())).thenReturn(List.of(location));
        when(previousLocationRepository.getLocationById(location.getId())).thenReturn(location);

    }
    @Test
    void getLocationByIdAssertNotNull(){
        assertNotNull(previousLocationRepository.getLocationById(9));
    }

    @Test
    void getLocationByUserIdAssertNull() {

        assertNotNull(previousLocationRepository.getLocationByAppUser_Id(1));
    }
    @Test
    void getLocationByUserIdAssertEqual(){

        List<Location> locations = previousLocationRepository.getLocationByAppUser_Id(9);
        assertEquals(locations.get(0).getId(),9);
    }
    @Test
    void getLocationByUserIdAssertSizeOne(){
        List<Location> locations = previousLocationRepository.getLocationByAppUser_Id(9);
        assertEquals(locations.size(), 1);
    }

}
