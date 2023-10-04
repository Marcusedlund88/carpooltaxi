package com.example.carpooltaxi.SERVICE;

import com.example.carpooltaxi.BODY.LocationBody;
import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.Location;
import com.example.carpooltaxi.REPOSITORY.AppUserRepository;
import com.example.carpooltaxi.REPOSITORY.CurrentLocationRepository;
import com.example.carpooltaxi.REPOSITORY.PreviousLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrentLocationService {

    private final CurrentLocationRepository currentLocationRepository;

    private final PreviousLocationRepository previousLocationRepository;

    private final AppUserRepository appUserRepository;

    private AppUser appUser;
    private Location previousLocation;
    private Location newLocation;


    public void makeCurrentLocation(LocationBody locationBody){

        if(currentLocationRepository.getLocationByAppUser_Id(locationBody.getUserId())!= null){

            previousLocation = currentLocationRepository.getLocationByAppUser_Id(locationBody.getUserId()).get(0);
            appUser = appUserRepository.getAppUserById(locationBody.getUserId());
            previousLocationRepository.save(previousLocation);
            currentLocationRepository.save(new Location(appUser, locationBody.getLongitude(), locationBody.getLatitude()));
        }
        else {
            appUser = appUserRepository.getAppUserById(locationBody.getUserId());
            currentLocationRepository.save(new Location(appUser, locationBody.getLongitude(), locationBody.getLatitude()));
        }
    }
}
