package com.example.carpooltaxi.DATA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LocationTest {

    private Location location;
    private Location nullLocation;
    private AppUser appUser;

    @BeforeEach
    public void makeLocation(){
        appUser = new AppUser(1,"dummyName");
        location = new Location(1, appUser,"dummyLong","dummyLat");
        nullLocation = new Location();
    }

    @Test
    public void getIdExpectEqual(){
        assertEquals(location.getId(),1);
    }

    @Test
    public void setIdExpectNotNull(){
        nullLocation.setId(1);
        assertNotNull(nullLocation.getId());
    }

    @Test
    public void getLongitudeExpectEqual(){
        assertEquals(location.getLongitude(), "dummyLong");
    }

    @Test
    public void setLongitudeExpectNotNull(){
        nullLocation.setLongitude("dummyLongitude");
        assertNotNull(nullLocation.getLongitude());
    }

    @Test
    public void getLatitudeExpectEqual(){
        assertEquals(location.getLatitude(), "dummyLat");
    }

    @Test
    public void setLatitudeExpectNotNull(){
        nullLocation.setLatitude("dummyLatitude");
        assertNotNull(nullLocation.getLatitude());
    }

    @Test
    public void getUserExpectEqual(){
        assertEquals(location.getAppUser(), appUser);
    }

    @Test
    public void setUserExpectEqual(){
        AppUser nullAppUser = new AppUser();
        nullLocation.setAppUser(nullAppUser);
        assertEquals(nullLocation.getAppUser(), nullAppUser);
    }
}
