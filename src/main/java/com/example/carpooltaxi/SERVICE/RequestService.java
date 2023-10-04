package com.example.carpooltaxi.SERVICE;

import com.example.carpooltaxi.BODY.RequestBodyConnection;
import com.example.carpooltaxi.DATA.Connection;
import com.example.carpooltaxi.REPOSITORY.AppUserRepository;
import com.example.carpooltaxi.REPOSITORY.ConnectionEstablishedRepository;
import com.example.carpooltaxi.REPOSITORY.ConnectionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final ConnectionRequestRepository connectionRequestRepository;
    private final ConnectionEstablishedRepository connectionEstablishedRepository;
    private final AppUserRepository appUserRepository;

    public String establishConnection(RequestBodyConnection requestBodyConnection){
        if(connectionEstablishedRepository.getConnectionByReceiverUserId(requestBodyConnection.getIdReceiver()) != null){
            return "User are busy";
        }
        else{
            try {
                connectionRequestRepository.save(new Connection(appUserRepository.getAppUserById(requestBodyConnection.getIdSender())
                        , appUserRepository.getAppUserById(requestBodyConnection.getIdReceiver())));
                return "Wait for user response";
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return "Ooops something went wrong";
    }

    public String establishConnectionGranted(RequestBodyConnection requestBodyConnection){
        connectionEstablishedRepository.save(
                new Connection(
                        appUserRepository.getAppUserById(requestBodyConnection.getIdSender()),
                        appUserRepository.getAppUserById(requestBodyConnection.getIdReceiver()
                )));

        connectionRequestRepository.deleteById(
                connectionRequestRepository.getConnectionBySenderUserId(requestBodyConnection.getIdSender()).getId()
        );
        return "Connection Established";
    }

}
