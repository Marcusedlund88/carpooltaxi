package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.CurrentLocation;
import com.example.carpooltaxi.DATA.PreviousLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PreviousCurrentLocationRepositoryTest {


    @Mock
    private PreviousLocationRepository previousLocationRepository;

    @InjectMocks
    private PreviousLocation previousLocation;

    @InjectMocks
    private AppUser appUser;

    @BeforeEach
    public void insertValueToMockDb(){
        MockitoAnnotations.openMocks(this);

        appUser = new AppUser(9, "dummyOne");
        previousLocation = new PreviousLocation(9, appUser, "dummyLong", "dummyLat");

        when(previousLocationRepository.save(previousLocation)).thenReturn(previousLocation);
        when(previousLocationRepository.findAll()).thenReturn(List.of(previousLocation));
        when(previousLocationRepository.getLocationByAppUser_Id(appUser.getId())).thenReturn(List.of(previousLocation));
        when(previousLocationRepository.getLocationById(previousLocation.getId())).thenReturn(previousLocation);

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

        List<PreviousLocation> currentLocations = previousLocationRepository.getLocationByAppUser_Id(9);
        assertEquals(currentLocations.get(0).getId(),9);
    }
    @Test
    void getLocationByUserIdAssertSizeOne(){
        List<PreviousLocation> currentLocations = previousLocationRepository.getLocationByAppUser_Id(9);
        assertEquals(currentLocations.size(), 1);
    }

}
