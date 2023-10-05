package com.example.carpooltaxi.DATA;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.ConnectionEstablish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConnectionEstablishTest {

    private ConnectionEstablish connectionEstablish;
    private ConnectionEstablish nullConnectionEstablished;
    private AppUser userOne;
    private AppUser userTwo;

    @BeforeEach
    public void makeDummyConnection(){
        userOne = new AppUser(1, "dummyOne");
        userTwo = new AppUser(2, "dummyTwo;");
        connectionEstablish = new ConnectionEstablish(1,userOne,userTwo);
        nullConnectionEstablished = new ConnectionEstablish();
    }
    @Test
    public void testReflexivity(){
        assertTrue(connectionEstablish.equals(connectionEstablish));
    }

    @Test
    public void testHashCode(){
        ConnectionEstablish connectionEstablish1 = new ConnectionEstablish(1,userOne,userTwo);
        assertEquals(connectionEstablish.hashCode(), connectionEstablish1.hashCode());
    }

    @Test
    public void getConnectionAssertNotNull(){
        assertNotNull(connectionEstablish);
    }

    @Test
    public void getConnectionIdAssertEqual(){
        assertEquals(connectionEstablish.getId(), 1);
    }

    @Test
    public void setConnectionIdAssertNotNull(){
        nullConnectionEstablished.setId(2);
        assertEquals(nullConnectionEstablished.getId(), 2);
    }

    @Test
    public void getSenderUserAssertEquals(){
        assertEquals(userOne, connectionEstablish.getSenderUser());
    }

    @Test
    public void getReceiverUserAssertEquals(){
        assertEquals(userTwo, connectionEstablish.getReceiverUser());
    }

    @Test
    public void setSenderUserAssertNotNull(){
        nullConnectionEstablished.setSenderUser(userOne);
        assertNotNull(nullConnectionEstablished.getSenderUser());
    }

    @Test
    public void setReceiverUserAssertNotNull(){
        nullConnectionEstablished.setReceiverUser(userTwo);
        assertNotNull(nullConnectionEstablished.getReceiverUser());
    }


}