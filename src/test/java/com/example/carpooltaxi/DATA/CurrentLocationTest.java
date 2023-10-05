package com.example.carpooltaxi.DATA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CurrentLocationTest {

    private CurrentLocation currentLocation;
    private CurrentLocation nullCurrentLocation;
    private AppUser appUser;

    @BeforeEach
    public void makeLocation(){
        appUser = new AppUser(1,"dummyName");
        currentLocation = new CurrentLocation(1, appUser,"dummyLong","dummyLat");
        nullCurrentLocation = new CurrentLocation();
    }

    @Test
    public void getIdExpectEqual(){
        assertEquals(currentLocation.getId(),1);
    }

    @Test
    public void setIdExpectNotNull(){
        nullCurrentLocation.setId(1);
        assertNotNull(nullCurrentLocation.getId());
    }

    @Test
    public void getLongitudeExpectEqual(){
        assertEquals(currentLocation.getLongitude(), "dummyLong");
    }

    @Test
    public void setLongitudeExpectNotNull(){
        nullCurrentLocation.setLongitude("dummyLongitude");
        assertNotNull(nullCurrentLocation.getLongitude());
    }

    @Test
    public void getLatitudeExpectEqual(){
        assertEquals(currentLocation.getLatitude(), "dummyLat");
    }

    @Test
    public void setLatitudeExpectNotNull(){
        nullCurrentLocation.setLatitude("dummyLatitude");
        assertNotNull(nullCurrentLocation.getLatitude());
    }

    @Test
    public void getUserExpectEqual(){
        assertEquals(currentLocation.getAppUser(), appUser);
    }

    @Test
    public void setUserExpectEqual(){
        AppUser nullAppUser = new AppUser();
        nullCurrentLocation.setAppUser(nullAppUser);
        assertEquals(nullCurrentLocation.getAppUser(), nullAppUser);
    }
}
