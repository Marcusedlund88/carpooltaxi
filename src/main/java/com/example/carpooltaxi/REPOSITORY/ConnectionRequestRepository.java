package com.example.carpooltaxi.REPOSITORY;

import com.example.carpooltaxi.DATA.ConnectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Long> {
    ConnectionRequest getConnectionBySenderUserId(long id);
    ConnectionRequest getConnectionByReceiverUserId(long id);
    void deleteById(long id);
    ConnectionRequest getConnectionById(long id);
    List<ConnectionRequest> findAllByReceiverUserId(long id);
}
