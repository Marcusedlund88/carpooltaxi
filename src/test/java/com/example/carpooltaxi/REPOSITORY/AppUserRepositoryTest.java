package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest()
class AppUserRepositoryTest {

    @Mock
    AppUserRepository appUserRepository;

    @InjectMocks
    private AppUser appUser;

    @BeforeEach
    public void insertValueToMockDb(){
        MockitoAnnotations.openMocks(this);

        appUser = new AppUser(1,"dummyOne");

        when(appUserRepository.save(appUser)).thenReturn(appUser);
        when(appUserRepository.findAll()).thenReturn(List.of(appUser));
    }

    @Test
    public void findAllUsers(){
        List<AppUser> appUsers = appUserRepository.findAll();
        assertEquals(appUsers.size(), 1);
    }

}
