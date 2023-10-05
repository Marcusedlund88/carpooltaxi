package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.ConnectionRequest;
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
class ConnectionRequestRequestRepositoryTest {

    @Mock
    private ConnectionRequestRepository connectionRequestRepository;

    @InjectMocks
    private ConnectionRequest connectionRequest;

    @InjectMocks
    private AppUser appUserOne;

    @InjectMocks
    private AppUser appUserTwo;

    @BeforeEach
    public void insertValuesToMockDb(){

        MockitoAnnotations.openMocks(this);

        appUserOne = new AppUser(1,"dummyOne");
        appUserTwo = new AppUser(5, "dummyTwo");

        connectionRequest = new ConnectionRequest(3, appUserOne, appUserTwo);

        when(connectionRequestRepository.save(connectionRequest)).thenReturn(connectionRequest);
        when(connectionRequestRepository.findAll()).thenReturn(List.of(connectionRequest));
        when(connectionRequestRepository.getConnectionByReceiverUserId(appUserTwo.getId())).thenReturn(connectionRequest);
    }

    @Test
    public void getConnectionAssertNotNull(){
        assertNotNull(connectionRequestRepository.findAll());
    }

    @Test
    public void getReceiverAppUserIdAssertCorrectValue(){
        assertEquals(connectionRequest.getReceiverUser().getId(),5);
    }

    @Test
    public void getConnectionByReceiverId(){
        connectionRequest = connectionRequestRepository.getConnectionByReceiverUserId(5);
        assertEquals(connectionRequest.getReceiverUser().getId(),5);
    }
}