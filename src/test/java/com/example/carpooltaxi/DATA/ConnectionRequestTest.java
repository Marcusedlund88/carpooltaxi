package com.example.carpooltaxi.DATA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConnectionRequestTest {

    private ConnectionRequest connectionRequest;
    private ConnectionRequest nullConnectionRequest;
    private AppUser userOne;
    private AppUser userTwo;

    @BeforeEach
    public void makeDummyConnection(){
        userOne = new AppUser(1, "dummyOne");
        userTwo = new AppUser(2, "dummyTwo;");
        connectionRequest = new ConnectionRequest(1,userOne,userTwo);
        nullConnectionRequest = new ConnectionRequest();
    }

    @Test
    public void getConnectionAssertNotNull(){
        assertNotNull(connectionRequest);
    }

    @Test
    public void getConnectionIdAssertEqual(){
        assertEquals(connectionRequest.getId(), 1);
    }

    @Test
    public void setConnectionIdAssertNotNull(){
        nullConnectionRequest.setId(2);
        assertEquals(nullConnectionRequest.getId(), 2);
    }

    @Test
    public void getSenderUserAssertEquals(){
        assertEquals(userOne, connectionRequest.getSenderUser());
    }

    @Test
    public void getReceiverUserAssertEquals(){
        assertEquals(userTwo, connectionRequest.getReceiverUser());
    }

    @Test
    public void setSenderUserAssertNotNull(){
        nullConnectionRequest.setSenderUser(userOne);
        assertNotNull(nullConnectionRequest.getSenderUser());
    }

    @Test
    public void setReceiverUserAssertNotNull(){
        nullConnectionRequest.setReceiverUser(userTwo);
        assertNotNull(nullConnectionRequest.getReceiverUser());
    }


}