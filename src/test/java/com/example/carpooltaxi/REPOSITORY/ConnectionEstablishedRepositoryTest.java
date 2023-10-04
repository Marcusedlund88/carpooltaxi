
package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.Connection;
import com.example.carpooltaxi.REPOSITORY.ConnectionEstablishedRepository;
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
class ConnectionEstablishedRepositoryTest {


    @Mock
    ConnectionEstablishedRepository connectionEstablishedRepository;

    @InjectMocks
    private Connection connection;
    @InjectMocks
    private AppUser appUserOne;
    @InjectMocks
    private AppUser appUserTwo;

    @BeforeEach
    public void insertValueToMockDb(){

        MockitoAnnotations.openMocks(this);
        appUserOne = new AppUser(1, "dummyOne");
        appUserTwo = new AppUser(2, "dummyTwo");
        connection = new Connection(1, appUserOne, appUserTwo);
        when(connectionEstablishedRepository.save(connection)).thenReturn(connection);
        when(connectionEstablishedRepository.findAll()).thenReturn(List.of(connection));
        when(connectionEstablishedRepository.getConnectionBySenderUserId(appUserOne.getId())).thenReturn(connection);

        connectionEstablishedRepository.save(connection);
    }

    @Test
    public void test(){
        List<Connection> connections = connectionEstablishedRepository.findAll();
        assertNotNull(connections);
    }
    @Test
    public void getConnectionByUserIdAssertEqual(){
        assertEquals(connection, connectionEstablishedRepository.getConnectionBySenderUserId(1));
    }
}
