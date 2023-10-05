package com.example.carpooltaxi.SERVICE;

import com.example.carpooltaxi.BODY.LocationBody;
import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.Location;
import com.example.carpooltaxi.REPOSITORY.AppUserRepository;
import com.example.carpooltaxi.REPOSITORY.CurrentLocationRepository;
import com.example.carpooltaxi.REPOSITORY.PreviousLocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CurrentLocationServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private CurrentLocationRepository currentLocationRepository;

    @Mock
    private PreviousLocationRepository previousLocationRepository;

    private AppUser appUser;

    @InjectMocks
    private Location previousLocation;

    @InjectMocks
    private Location newLocation;

    @Mock
    private CurrentLocationService currentLocationService;


    @InjectMocks
    private LocationBody locationBody;

    @BeforeEach
    private void createMockEnvironment(){

        MockitoAnnotations.openMocks(this);

        appUser = new AppUser(1, "dummyOne");
        previousLocation = new Location(1, appUser, "dummyLongitudeOne", "dummyLatitudeOne");

        LocationBody locationBody = new LocationBody();
        locationBody.setUserId(1);
        locationBody.setLongitude("updatedDummyLong");
        locationBody.setLatitude("updatedDummyLat");

        when(currentLocationRepository.save(previousLocation)).thenReturn(previousLocation);
        when(currentLocationRepository.findAll()).thenReturn(List.of(previousLocation));
        when(currentLocationRepository.getLocationByAppUser_Id(appUser.getId())).thenReturn(List.of(previousLocation));
        when(previousLocationRepository.save(previousLocation)).thenReturn(previousLocation);
        when(previousLocationRepository.getLocationByAppUser_Id(appUser.getId())).thenReturn(List.of(previousLocation));

        currentLocationRepository.save(previousLocation);

        makeCurrentLocation(locationBody);

    }

    @Test
    public void getCurrentLocationByUserIdAssertOne(){
        assertEquals(currentLocationRepository.getLocationByAppUser_Id(1).size(), 1);
    }
    @Test
    public void getPreviousLocationByUserIdAssertOne(){
        assertEquals(previousLocationRepository.getLocationByAppUser_Id(1).size(), 1);
    }
    public void makeCurrentLocation(LocationBody locationBody){

        if(currentLocationRepository.getLocationByAppUser_Id(locationBody.getUserId())!= null){
            previousLocation = currentLocationRepository.getLocationByAppUser_Id(locationBody.getUserId()).get(0);
            appUser = appUserRepository.getAppUserById(locationBody.getUserId());
            previousLocationRepository.save(previousLocation);
            currentLocationRepository.save(new Location(appUser, locationBody.getLongitude(), locationBody.getLatitude()));

        }
        else {
            appUser = appUserRepository.getAppUserById(locationBody.getUserId());
            currentLocationRepository.save(new Location(appUser, locationBody.getLongitude(), locationBody.getLatitude()));
        }

    }
}