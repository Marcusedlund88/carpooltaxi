package com.example.carpooltaxi.DATA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConnectionTest {

    private Connection connection;
    private Connection nullConnection;
    private AppUser userOne;
    private AppUser userTwo;

    @BeforeEach
    public void makeDummyConnection(){
        userOne = new AppUser(1, "dummyOne");
        userTwo = new AppUser(2, "dummyTwo;");
        connection = new Connection(1,userOne,userTwo);
        nullConnection = new Connection();
    }

    @Test
    public void getConnectionAssertNotNull(){
        assertNotNull(connection);
    }

    @Test
    public void getConnectionIdAssertEqual(){
        assertEquals(connection.getId(), 1);
    }

    @Test
    public void setConnectionIdAssertNotNull(){
        nullConnection.setId(2);
        assertEquals(nullConnection.getId(), 2);
    }

    @Test
    public void getSenderUserAssertEquals(){
        assertEquals(userOne, connection.getSenderUser());
    }

    @Test
    public void getReceiverUserAssertEquals(){
        assertEquals(userTwo, connection.getReceiverUser());
    }

    @Test
    public void setSenderUserAssertNotNull(){
        nullConnection.setSenderUser(userOne);
        assertNotNull(nullConnection.getSenderUser());
    }

    @Test
    public void setReceiverUserAssertNotNull(){
        nullConnection.setReceiverUser(userTwo);
        assertNotNull(nullConnection.getReceiverUser());
    }


}