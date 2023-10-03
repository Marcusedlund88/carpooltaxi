package com.example.carpooltaxi.CONFIG;

import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.Location;
import com.example.carpooltaxi.REPOSITORY.AppUserRepository;
import com.example.carpooltaxi.REPOSITORY.CurrentLocationRepository;
import com.example.carpooltaxi.REPOSITORY.PreviousLocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CmdLineRunner {

    @Bean
    CommandLineRunner commandLineRunner(AppUserRepository appUserRepository
            , CurrentLocationRepository currentLocationRepository
            , PreviousLocationRepository previousLocationRepository){
        return args->{

            appUserRepository.save(new AppUser("dummyOne"));
            appUserRepository.save(new AppUser("dummyTwo"));
            appUserRepository.save(new AppUser("dummyThree"));
            appUserRepository.save(new AppUser("dummyFour"));
            appUserRepository.save(new AppUser("dummyFive"));

            currentLocationRepository.save(new Location(appUserRepository.findByName("dummyOne")
                    , "dummyLongOne"
                    , "dummyLatOne"));

            currentLocationRepository.save(new Location(appUserRepository.findByName("dummyTwo")
                    , "dummyLongTwo"
                    , "dummyLatTwo"));

            currentLocationRepository.save(new Location(appUserRepository.findByName("dummyThree")
                    , "dummyLongThree"
                    , "dummyLatThree"));

            currentLocationRepository.save(new Location(appUserRepository.findByName("dummyFour")
                    , "dummyLongFour"
                    , "dummyLatFour"));

            currentLocationRepository.save(new Location(appUserRepository.findByName("dummyFive")
                    , "dummyLongFive"
                    , "dummyLatFive"));

            previousLocationRepository.save(currentLocationRepository.getLocationById(1));

            previousLocationRepository.save(currentLocationRepository.getLocationById(2));
        };
    }

}
