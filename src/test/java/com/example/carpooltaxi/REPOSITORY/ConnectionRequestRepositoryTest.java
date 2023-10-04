package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class ConnectionRequestRepositoryTest {

    @Mock
    private ConnectionRequestRepository connectionRequestRepository;

    @InjectMocks
    private Connection connection;

    @InjectMocks
    private AppUser appUserOne;

    @InjectMocks
    private AppUser appUserTwo;

    @BeforeEach
    public void insertValuesToMockDb(){

        MockitoAnnotations.openMocks(this);

        appUserOne = new AppUser(1,"dummyOne");
        appUserTwo = new AppUser(5, "dummyTwo");

        connection = new Connection(3, appUserOne, appUserTwo);

        when(connectionRequestRepository.save(connection)).thenReturn(connection);
        when(connectionRequestRepository.findAll()).thenReturn(List.of(connection));
    }

    @Test
    public void getConnectionAssertNotNull(){
        assertNotNull(connectionRequestRepository.findAll());
    }

    @Test
    public void getReceiverAppUserIdAssertCorrectValue(){
        assertEquals(connection.getReceiverUser().getId(),5);
    }
}