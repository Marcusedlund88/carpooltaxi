package com.example.carpooltaxi.DATA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PreviousLocationTest {

    private PreviousLocation previousLocation;
    private PreviousLocation nullPreviousLocation;
    private AppUser appUser;

    @BeforeEach
    public void makeLocation(){
        appUser = new AppUser(1,"dummyName");
        previousLocation = new PreviousLocation(1, appUser,"dummyLong","dummyLat");
        nullPreviousLocation = new PreviousLocation();
    }

    @Test
    public void testReflexivity(){
        assertTrue(previousLocation.equals(previousLocation));
    }
    @Test
    public void testHashCode(){
        PreviousLocation newCurrentLocation =  new PreviousLocation(1, appUser,"dummyLong","dummyLat");
        assertEquals(previousLocation.hashCode(), newCurrentLocation.hashCode());
    }
    @Test
    public void getIdExpectEqual(){
        assertEquals(previousLocation.getId(),1);
    }

    @Test
    public void setIdExpectNotNull(){
        nullPreviousLocation.setId(1);
        assertNotNull(nullPreviousLocation.getId());
    }

    @Test
    public void getLongitudeExpectEqual(){
        assertEquals(previousLocation.getLongitude(), "dummyLong");
    }

    @Test
    public void setLongitudeExpectNotNull(){
        nullPreviousLocation.setLongitude("dummyLongitude");
        assertNotNull(nullPreviousLocation.getLongitude());
    }

    @Test
    public void getLatitudeExpectEqual(){
        assertEquals(previousLocation.getLatitude(), "dummyLat");
    }

    @Test
    public void setLatitudeExpectNotNull(){
        nullPreviousLocation.setLatitude("dummyLatitude");
        assertNotNull(nullPreviousLocation.getLatitude());
    }

    @Test
    public void getUserExpectEqual(){
        assertEquals(previousLocation.getAppUser(), appUser);
    }

    @Test
    public void setUserExpectEqual(){
        AppUser nullAppUser = new AppUser();
        nullPreviousLocation.setAppUser(nullAppUser);
        assertEquals(nullPreviousLocation.getAppUser(), nullAppUser);
    }
}