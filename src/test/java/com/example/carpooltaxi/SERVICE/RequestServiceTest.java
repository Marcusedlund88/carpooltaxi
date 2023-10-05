package com.example.carpooltaxi.SERVICE;

import com.example.carpooltaxi.BODY.RequestBodyConnection;
import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.ConnectionEstablish;
import com.example.carpooltaxi.DATA.ConnectionRequest;
import com.example.carpooltaxi.REPOSITORY.AppUserRepository;
import com.example.carpooltaxi.REPOSITORY.ConnectionEstablishedRepository;
import com.example.carpooltaxi.REPOSITORY.ConnectionRequestRepository;
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
class RequestServiceTest {

    @Mock
    private ConnectionEstablishedRepository connectionEstablishedRepository;

    @Mock
    private ConnectionRequestRepository connectionRequestRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private ConnectionRequest connectionRequest;

    @InjectMocks
    private ConnectionEstablish connectionEstablish;

    @InjectMocks
    private ConnectionRequest connectionRequestTwo;

    private RequestService requestService;
    private AppUser appUserOne;
    private AppUser appUserTwo;

    private long id;

    @BeforeEach
    private void setUpMockEnvironment() {

        MockitoAnnotations.openMocks(this);

        appUserOne = new AppUser(1, "dummyOne");
        appUserTwo = new AppUser(2, "dummyTwo");

        connectionRequest = new ConnectionRequest(1, appUserOne, appUserTwo);

        when(connectionEstablishedRepository.save(connectionEstablish)).thenReturn(connectionEstablish);
        when(connectionEstablishedRepository.findAll()).thenReturn(List.of(connectionEstablish));
        when(connectionEstablishedRepository.getConnectionByReceiverUserId(appUserTwo.getId())).thenReturn(connectionEstablish);

        when(connectionRequestRepository.save(connectionRequest)).thenReturn(connectionRequest);
        when(connectionRequestRepository.findAll()).thenReturn(List.of(connectionRequest));
        when(connectionRequestRepository.getConnectionByReceiverUserId(appUserTwo.getId())).thenReturn(connectionRequest);
        when(connectionRequestRepository.findAllByReceiverUserId(appUserTwo.getId())).thenReturn(List.of(connectionRequest));
        when(connectionRequestRepository.getConnectionBySenderUserId(appUserOne.getId())).thenReturn(connectionRequest);
        when(connectionRequestRepository.getConnectionByReceiverUserId(appUserTwo.getId())).thenReturn(connectionRequest);

        when(appUserRepository.save(appUserOne)).thenReturn(appUserOne);
        when(appUserRepository.getAppUserById(appUserOne.getId())).thenReturn(appUserOne);
        when(appUserRepository.save(appUserTwo)).thenReturn(appUserTwo);
        when(appUserRepository.getAppUserById(appUserTwo.getId())).thenReturn(appUserTwo);

        appUserRepository.save(appUserOne);
        appUserRepository.save(appUserTwo);

        requestService = new RequestService(connectionRequestRepository, connectionEstablishedRepository, appUserRepository);
    }

    @Test
    public void establishConnectionExpectBusy() {
        appUserRepository.save(appUserOne);
        appUserRepository.save(appUserTwo);
        connectionEstablishedRepository.save(connectionEstablish);
        RequestBodyConnection requestBodyConnection = new RequestBodyConnection();
        requestBodyConnection.setIdSender(appUserOne.getId());
        requestBodyConnection.setIdReceiver(appUserTwo.getId());
        requestService.establishConnection(requestBodyConnection);

        assertEquals(requestService.establishConnection(requestBodyConnection), "User are busy");
    }

    @Test
    public void establishConnectionExpectWaitForResponse() {
        AppUser appUser3 = new AppUser(4,"dummyfour");
        AppUser appUser4 = new AppUser(3,"dummyThree");

        when(appUserRepository.save(appUser3)).thenReturn(appUser3);
        when(appUserRepository.getAppUserById(appUser3.getId())).thenReturn(appUser3);
        when(appUserRepository.save(appUser4)).thenReturn(appUser4);
        when(appUserRepository.getAppUserById(appUser4.getId())).thenReturn(appUser4);
        appUserRepository.save(appUser4);

        RequestBodyConnection requestBodyConnection = new RequestBodyConnection();
        requestBodyConnection.setIdSender(3);
        requestBodyConnection.setIdReceiver(4);

        assertEquals(requestService.establishConnection(requestBodyConnection), "Wait for user response");
    }

    @Test
    public void connectionEstablishedExpectOK() {

        RequestBodyConnection requestBodyConnection = new RequestBodyConnection();
        requestBodyConnection.setIdSender(2);
        requestBodyConnection.setIdReceiver(1);

        assertEquals(requestService.establishConnectionGranted(requestBodyConnection), "Connection Established");
    }

    @Test
    public void connectionEstablishedExpectFoundInRepo() {
        RequestBodyConnection requestBodyConnection = new RequestBodyConnection();
        requestBodyConnection.setIdSender(2);
        requestBodyConnection.setIdReceiver(1);
        requestService.establishConnectionGranted(requestBodyConnection);

        assertNotNull(connectionRequestRepository.getConnectionByReceiverUserId(2));
    }
}