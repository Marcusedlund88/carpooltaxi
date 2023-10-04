package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionEstablishedRepository extends JpaRepository<Connection, Long> {

    Connection getConnectionBySenderUserId(long id);
    Connection getConnectionByReceiverUserId(long id);

}
