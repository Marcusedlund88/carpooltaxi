package com.example.carpooltaxi.CONTROLLER;

import com.example.carpooltaxi.BODY.RequestBodyConnection;
import com.example.carpooltaxi.DATA.ConnectionEstablish;
import com.example.carpooltaxi.DATA.ConnectionRequest;
import com.example.carpooltaxi.REPOSITORY.*;
import com.example.carpooltaxi.SERVICE.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connection")
@RequiredArgsConstructor
public class ConnectionController {

    private final AppUserRepository appUserRepository;
    private final CurrentLocationRepository currentLocationRepository;
    private final PreviousLocationRepository previousLocationRepository;
    private final ConnectionRequestRepository connectionRequestRepository;
    private final ConnectionEstablishedRepository connectionEstablishedRepository;
    private final RequestService requestService;

    @GetMapping("/established/all")
    public ResponseEntity<List<ConnectionEstablish>> getAllEstablishedConnections(){
        return new ResponseEntity<List<ConnectionEstablish>>(connectionEstablishedRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/requested/all")
    public ResponseEntity<List<ConnectionRequest>> getAllRequestedConnections(){
        return new ResponseEntity<List<ConnectionRequest>>(connectionRequestRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/request/send")
    public ResponseEntity<String> sendConnectionRequest(@RequestBody RequestBodyConnection requestBodyConnection){

        try {
            return new ResponseEntity<String>
                    (requestService.establishConnection(requestBodyConnection)
                            , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>("Format error",HttpStatus.OK);
        }
    }
    @PostMapping("/request/grant")
    public ResponseEntity<String> grantConnectionRequest(@RequestBody RequestBodyConnection requestBodyConnection){
        System.out.println(requestBodyConnection);
        try {
            return new ResponseEntity<String>
                    (requestService.establishConnectionGranted(requestBodyConnection)
                            , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>("Format error",HttpStatus.OK);
        }
    }

}
