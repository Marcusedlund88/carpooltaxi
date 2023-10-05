package com.example.carpooltaxi.CONTROLLER;

import com.example.carpooltaxi.REPOSITORY.*;
import com.example.carpooltaxi.SERVICE.RequestService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@Import(ConnectionController.class)
class ConnectionControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private CurrentLocationRepository currentLocationRepository;
    @Autowired
    private PreviousLocationRepository previousLocationRepository;
    @Autowired
    private ConnectionRequestRepository connectionRequestRepository;
    @Autowired
    private ConnectionEstablishedRepository connectionEstablishedRepository;
    @Autowired
    private RequestService requestService;

    private MockMvc mockMvc;
    private JSONObject jsonObject;

    @BeforeEach
    public void makeRequestObject() throws JSONException {
        jsonObject = new JSONObject();
        jsonObject.put("idSender", "1");
        jsonObject.put("idReceiver", "2");

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void getAllEstablishedConnections() throws Exception {

        mockMvc.perform(get("/connection/established/all")).andExpect(status().isOk());
    }

    @Test
    void getAllRequestedConnections() throws Exception {

        mockMvc.perform(get("/connection/established/all")).andExpect(status().isOk());
    }

    @Test
    void sendConnectionRequest() throws Exception {

        mockMvc.perform(post("/connection/request/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("Wait for user response"));
    }

    @Test
    void grantConnectionRequest() throws Exception {
        mockMvc.perform(post("/connection/request/grant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("Connection Refused"));
    }
}