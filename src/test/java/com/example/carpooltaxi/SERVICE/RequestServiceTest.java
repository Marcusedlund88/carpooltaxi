package com.example.carpooltaxi.SERVICE;

import com.example.carpooltaxi.BODY.RequestBodyConnection;
import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.Connection;
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
    private Connection connection;

    @InjectMocks
    private Connection connectionTwo;

    @InjectMocks
    private AppUser appUserOne;

    @InjectMocks
    private AppUser appUserTwo;

    private long id;

    @BeforeEach
    private void setUpMockEnvironment(){

        MockitoAnnotations.openMocks(this);

        appUserOne = new AppUser(1,"dummyOne");
        appUserTwo = new AppUser(2,"dummyTwo");

        connection = new Connection(1,appUserOne,appUserTwo);

        when(connectionEstablishedRepository.save(connection)).thenReturn(connection);
        when(connectionEstablishedRepository.findAll()).thenReturn(List.of(connection));
        when(connectionEstablishedRepository.getConnectionByReceiverUserId(appUserTwo.getId())).thenReturn(connection);

        when(connectionRequestRepository.save(connection)).thenReturn(connection);
        when(connectionRequestRepository.findAll()).thenReturn(List.of(connection));
        when(connectionRequestRepository.getConnectionByReceiverUserId(appUserTwo.getId())).thenReturn(connection);
    }

    @Test
    public void establishConnectionExpectBusy(){
        connectionEstablishedRepository.save(connection);
        RequestBodyConnection requestBodyConnection = new RequestBodyConnection();
        requestBodyConnection.setIdSender(appUserOne.getId());
        requestBodyConnection.setIdReceiver(appUserTwo.getId());

        assertEquals(establishConnection(requestBodyConnection),"User are busy");
    }

    @Test
    public void establishConnectionExpectWaitForResponse(){
        RequestBodyConnection requestBodyConnection = new RequestBodyConnection();
        requestBodyConnection.setIdSender(5);
        requestBodyConnection.setIdReceiver(6);

        assertEquals(establishConnection(requestBodyConnection),"Wait for user response");
    }

    @Test
    public void connectionEstablishedExpectOK(){
        RequestBodyConnection requestBodyConnection = new RequestBodyConnection();
        requestBodyConnection.setIdSender(5);
        requestBodyConnection.setIdReceiver(6);
        establishConnection(requestBodyConnection);
        assertEquals(establishConnectionGranted(requestBodyConnection),"Connection Established");
    }

    @Test
    public void connectionEstablishedExpectFoundInRepo(){
        RequestBodyConnection requestBodyConnection = new RequestBodyConnection();
        requestBodyConnection.setIdSender(1);
        requestBodyConnection.setIdReceiver(2);
        establishConnectionGranted(requestBodyConnection);

        assertNotNull(connectionRequestRepository.getConnectionByReceiverUserId(2));
    }

    public String establishConnection(RequestBodyConnection requestBodyConnection){
        if(connectionEstablishedRepository.getConnectionByReceiverUserId(requestBodyConnection.getIdReceiver()) != null){
            return "User are busy";
        }
        else{
            try {
                connectionRequestRepository.save(new Connection(appUserRepository.getAppUserById(requestBodyConnection.getIdSender())
                        , appUserRepository.getAppUserById(requestBodyConnection.getIdReceiver())));
                return "Wait for user response";
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return "Ooops something went wrong";
    }

    public String establishConnectionGranted(RequestBodyConnection requestBodyConnection){

        try{
            connection = connectionRequestRepository.getConnectionByReceiverUserId(requestBodyConnection.getIdReceiver());
            connectionEstablishedRepository.save(connection);
            connectionRequestRepository.delete(connection);
            return "Connection Established";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "Something went wrong";
    }
}