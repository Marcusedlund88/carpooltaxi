package com.example.carpooltaxi.Body;

public class RequestBodyConnection {

    private long idSender;
    private long idReceiver;

    public long getIdSender() {
        return idSender;
    }

    public void setIdSender(long idSender) {
        this.idSender = idSender;
    }

    public long getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(long idReceiver) {
        this.idReceiver = idReceiver;
    }
}
