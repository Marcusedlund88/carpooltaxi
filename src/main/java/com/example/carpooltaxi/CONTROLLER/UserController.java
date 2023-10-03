package com.example.carpooltaxi.CONTROLLER;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.REPOSITORY.AppUserRepository;
import com.example.carpooltaxi.REPOSITORY.CurrentLocationRepository;
import com.example.carpooltaxi.REPOSITORY.PreviousLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {

    private final AppUserRepository appUserRepository;
    private final CurrentLocationRepository currentLocationRepository;
    private final PreviousLocationRepository previousLocationRepository;

    @GetMapping
    public ResponseEntity<String> getAllUsers(){
        return new ResponseEntity<String>( "hello", HttpStatus.OK);
    }


}
