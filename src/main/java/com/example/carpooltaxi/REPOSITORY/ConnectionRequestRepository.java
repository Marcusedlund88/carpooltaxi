package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRequestRepository extends JpaRepository<Connection, Long> {
    Connection getConnectionBySenderUserId(long id);
    Connection getConnectionByReceiverUserId(long id);
    void deleteById(long id);
    Connection getConnectionById(long id);
}
