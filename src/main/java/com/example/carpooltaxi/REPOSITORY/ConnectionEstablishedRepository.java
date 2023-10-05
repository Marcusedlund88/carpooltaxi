package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.ConnectionEstablish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionEstablishedRepository extends JpaRepository<ConnectionEstablish, Long> {

    ConnectionEstablish getConnectionBySenderUserId(long id);
    ConnectionEstablish getConnectionByReceiverUserId(long id);

}
