package com.example.carpooltaxi.SERVICE;

import com.example.carpooltaxi.BODY.RequestBodyConnection;
import com.example.carpooltaxi.DATA.AppUser;
import com.example.carpooltaxi.DATA.ConnectionEstablish;
import com.example.carpooltaxi.DATA.ConnectionRequest;
import com.example.carpooltaxi.REPOSITORY.AppUserRepository;
import com.example.carpooltaxi.REPOSITORY.ConnectionEstablishedRepository;
import com.example.carpooltaxi.REPOSITORY.ConnectionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final ConnectionRequestRepository connectionRequestRepository;
    private final ConnectionEstablishedRepository connectionEstablishedRepository;
    private final AppUserRepository appUserRepository;

    public String establishConnection(RequestBodyConnection requestBodyConnection){

        AppUser appUserOne = appUserRepository.getAppUserById(requestBodyConnection.getIdSender());
        AppUser appUserTwo= appUserRepository.getAppUserById(requestBodyConnection.getIdReceiver());
        if(appUserRepository.getAppUserById(requestBodyConnection.getIdSender()) != null
                &&
        appUserRepository.getAppUserById(requestBodyConnection.getIdReceiver()) != null) {
            if (connectionEstablishedRepository.getConnectionByReceiverUserId(requestBodyConnection.getIdReceiver()) != null) {
                return "User are busy";
            } else {
                try {
                    connectionRequestRepository.save(new ConnectionRequest(appUserRepository.getAppUserById(requestBodyConnection.getIdSender())
                            , appUserRepository.getAppUserById(requestBodyConnection.getIdReceiver())));
                    return "Wait for user response";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "Ooops something went wrong";
        }else {
            return "no such user in db";
        }
    }

    public String establishConnectionGranted(RequestBodyConnection requestBodyConnection){
        List<ConnectionRequest> connectionRequests = connectionRequestRepository
                .findAllByReceiverUserId(requestBodyConnection.getIdSender());

        if(connectionRequests
                .stream()
                .anyMatch(index -> index.getSenderUser().getId() == requestBodyConnection.getIdReceiver()
                && index.getReceiverUser().getId() == requestBodyConnection.getIdSender())){

            connectionEstablishedRepository.save(
                                new ConnectionEstablish(
                                        appUserRepository.getAppUserById(requestBodyConnection.getIdSender()),
                                        appUserRepository.getAppUserById(requestBodyConnection.getIdReceiver())
                                )
                        );
                        connectionRequestRepository.deleteById(
                                connectionRequestRepository.getConnectionBySenderUserId(requestBodyConnection.getIdReceiver()).getId());

                        return "Connection Established";
        }
                else{
                return "Connection Refused";
        }

    }

}
