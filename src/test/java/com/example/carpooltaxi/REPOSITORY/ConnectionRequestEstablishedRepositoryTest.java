
package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.ConnectionEstablish;
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
class ConnectionRequestEstablishedRepositoryTest {


    @Mock
    ConnectionEstablishedRepository connectionEstablishedRepository;

    @InjectMocks
    private ConnectionEstablish connectionEstablish;
    @InjectMocks
    private AppUser appUserOne;
    @InjectMocks
    private AppUser appUserTwo;

    @BeforeEach
    public void insertValueToMockDb(){

        MockitoAnnotations.openMocks(this);
        appUserOne = new AppUser(1, "dummyOne");
        appUserTwo = new AppUser(2, "dummyTwo");
        connectionEstablish = new ConnectionEstablish(1, appUserOne, appUserTwo);
        when(connectionEstablishedRepository.save(connectionEstablish)).thenReturn(connectionEstablish);
        when(connectionEstablishedRepository.findAll()).thenReturn(List.of(connectionEstablish));
        when(connectionEstablishedRepository.getConnectionBySenderUserId(appUserOne.getId())).thenReturn(connectionEstablish);

        connectionEstablishedRepository.save(connectionEstablish);
    }

    @Test
    public void test(){
        List<ConnectionEstablish> connectionRequests = connectionEstablishedRepository.findAll();
        assertNotNull(connectionRequests);
    }
    @Test
    public void getConnectionByUserIdAssertEqual(){
        assertEquals(connectionEstablish, connectionEstablishedRepository.getConnectionBySenderUserId(1));
    }
}
