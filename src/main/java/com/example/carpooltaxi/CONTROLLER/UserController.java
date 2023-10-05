package com.example.carpooltaxi.CONTROLLER;

import com.example.carpooltaxi.BODY.RequestBodyConnection;
import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.REPOSITORY.AppUserRepository;
import com.example.carpooltaxi.REPOSITORY.ConnectionRequestRepository;
import com.example.carpooltaxi.REPOSITORY.CurrentLocationRepository;
import com.example.carpooltaxi.REPOSITORY.PreviousLocationRepository;
import com.example.carpooltaxi.SERVICE.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AppUserRepository appUserRepository;
    private final CurrentLocationRepository currentLocationRepository;
    private final PreviousLocationRepository previousLocationRepository;
    private final ConnectionRequestRepository connectionRequestRepository;
    private final RequestService requestService;

    @GetMapping("")
    public void getAllUsers(){
        System.out.println("Hello");
    }
}
