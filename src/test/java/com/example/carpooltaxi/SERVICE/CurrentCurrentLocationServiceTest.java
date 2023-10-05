package com.example.carpooltaxi.SERVICE;

import com.example.carpooltaxi.BODY.LocationBody;
import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.CurrentLocation;
import com.example.carpooltaxi.DATA.PreviousLocation;
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
class CurrentCurrentLocationServiceTest {

    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    private CurrentLocationRepository currentLocationRepository;
    @Mock
    private PreviousLocationRepository previousLocationRepository;

    private AppUser appUser;
    private AppUser appUserTwo;
    @Mock
    private CurrentLocationService currentLocationService;
    private PreviousLocation previousCurrentLocation;
    private CurrentLocation newCurrentLocation;
    private LocationBody locationBody;
    @BeforeEach
    private void createMockEnvironment(){

        MockitoAnnotations.openMocks(this);

        appUser = new AppUser(1, "dummyOne");
        appUserTwo = new AppUser(2, "dummyTwo");
        newCurrentLocation  = new CurrentLocation(appUser, "dummyLongitudeOne", "dummyLatitudeOne");
        previousCurrentLocation  = new PreviousLocation(appUser, "dummyLongitudeTwo", "dummyLatitudeTwo");

        LocationBody locationBody = new LocationBody();
        locationBody.setUserId(1);
        locationBody.setLongitude("updatedDummyLong");
        locationBody.setLatitude("updatedDummyLat");

        when(currentLocationRepository.save(newCurrentLocation)).thenReturn(newCurrentLocation);
        when(currentLocationRepository.findAll()).thenReturn(List.of(newCurrentLocation));
        when(currentLocationRepository.getLocationByAppUser_Id(appUser.getId())).thenReturn(List.of(newCurrentLocation));
        when(currentLocationRepository.getLocationByAppUser_Id(appUserTwo.getId())).thenReturn(null);

        when(previousLocationRepository.save(previousCurrentLocation)).thenReturn(previousCurrentLocation);
        when(previousLocationRepository.getLocationByAppUser_Id(appUser.getId())).thenReturn(List.of(previousCurrentLocation));

        when(appUserRepository.save(appUser)).thenReturn(appUser);
        when(appUserRepository.getAppUserById(appUser.getId())).thenReturn(appUser);
        when(appUserRepository.save(appUserTwo)).thenReturn(appUserTwo);
        when(appUserRepository.getAppUserById(appUserTwo.getId())).thenReturn(appUserTwo);

        appUserRepository.save(appUser);
        currentLocationRepository.save(newCurrentLocation);

        currentLocationService = new CurrentLocationService(currentLocationRepository,previousLocationRepository, appUserRepository);

    }

    @Test
    public void getCurrentLocationByUserIdAssertOne(){

        List<CurrentLocation> currentLocations = currentLocationRepository.getLocationByAppUser_Id(1);
        assertEquals(currentLocationRepository.getLocationByAppUser_Id(1).size(), 1);
    }
    @Test
    public void getPreviousLocationByUserIdAssertOne(){
        assertEquals(previousLocationRepository.getLocationByAppUser_Id(1).size(), 1);
    }

    @Test
    public void makeNewLocationAssertPositionUpdated(){
        LocationBody locationBody = new LocationBody();
        locationBody.setUserId(1);
        locationBody.setLongitude("updatedDummyLong");
        locationBody.setLatitude("updatedDummyLat");

        assertEquals(currentLocationService.makeCurrentLocation(locationBody), "Position was updated");
    }
    @Test
    public void makeNewLocationAssertPositionEstablished(){
        LocationBody locationBody = new LocationBody();
        locationBody.setUserId(2);
        locationBody.setLongitude("updatedDummyLong");
        locationBody.setLatitude("updatedDummyLat");

        currentLocationService.makeCurrentLocation(locationBody);

        assertEquals(currentLocationService.makeCurrentLocation(locationBody), "Position was established");
    }

    @Test
    public void makeNewLocationAssertError(){
        LocationBody locationBody = new LocationBody();
        locationBody.setUserId(5);
        locationBody.setLongitude("updatedDummyLong");
        locationBody.setLatitude("updatedDummyLat");

        currentLocationService.makeCurrentLocation(locationBody);

        assertEquals(currentLocationService.makeCurrentLocation(locationBody), "Unexpected error");
    }
}