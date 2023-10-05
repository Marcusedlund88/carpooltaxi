package com.example.carpooltaxi.SERVICE;

import com.example.carpooltaxi.BODY.LocationBody;
import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.CurrentLocation;
import com.example.carpooltaxi.DATA.PreviousLocation;
import com.example.carpooltaxi.REPOSITORY.AppUserRepository;
import com.example.carpooltaxi.REPOSITORY.CurrentLocationRepository;
import com.example.carpooltaxi.REPOSITORY.PreviousLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentLocationService {

    private final CurrentLocationRepository currentLocationRepository;

    private final PreviousLocationRepository previousLocationRepository;

    private final AppUserRepository appUserRepository;

    private AppUser appUser;
    private PreviousLocation previousCurrentLocation;
    private CurrentLocation newCurrentLocation;


    public String makeCurrentLocation(LocationBody locationBody){

        if(appUserRepository.getAppUserById(locationBody.getUserId()) != null) {
            if (currentLocationRepository.getLocationByAppUser_Id(locationBody.getUserId()) != null) {

                newCurrentLocation = currentLocationRepository.getLocationByAppUser_Id(locationBody.getUserId()).get(0);
                appUser = appUserRepository.getAppUserById(locationBody.getUserId());
                previousCurrentLocation = new PreviousLocation(appUser, newCurrentLocation.getLongitude(), newCurrentLocation.getLatitude());
                previousLocationRepository.save(previousCurrentLocation);
                currentLocationRepository.save(new CurrentLocation(appUser, locationBody.getLongitude(), locationBody.getLatitude()));
                return "Position was updated";
            } else {
                appUser = appUserRepository.getAppUserById(locationBody.getUserId());
                currentLocationRepository.save(new CurrentLocation(appUser, locationBody.getLongitude(), locationBody.getLatitude()));
                return "Position was established";
            }
        }
        return "Unexpected error";
    }
}
