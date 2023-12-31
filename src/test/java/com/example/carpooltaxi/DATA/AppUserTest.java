package com.example.carpooltaxi.DATA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserTest {

    private AppUser appUser;
    private AppUser nullAppUser;

    @BeforeEach
    public void makeUser() {
        appUser = new AppUser(1, "dummyName");
        nullAppUser = new AppUser();
    }

    @Test
    public void testReflexivity(){
        assertTrue(appUser.equals(appUser));
    }

    @Test
    public void testHashCode(){
        AppUser appUser1 = new AppUser(1, "dummyName");
        assertEquals(appUser.hashCode(), appUser1.hashCode());
    }

    @Test
    public void getIdExpectEqual() {
        assertEquals(appUser.getId(), 1);
    }

    @Test
    public void setIdExpectNotNull() {
        nullAppUser.setId(1);
        assertNotNull(nullAppUser.getId());
    }

    @Test
    public void getNameExpectEqual() {
        assertEquals(appUser.getName(), "dummyName");
    }

    @Test
    public void setNameExpectNotNull() {
        nullAppUser.setName("dummyName");
        assertNotNull(nullAppUser.getName());
    }
}
