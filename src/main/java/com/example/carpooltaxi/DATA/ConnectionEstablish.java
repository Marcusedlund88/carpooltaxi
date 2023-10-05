package com.example.carpooltaxi.DATA;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionEstablish {


    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "sender_user_id")
    private AppUser senderUser;

    @OneToOne
    @JoinColumn(name = "receiver_user_id")
    private AppUser receiverUser;

    public ConnectionEstablish(AppUser senderUser, AppUser receiverUser) {
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AppUser getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(AppUser senderUser) {
        this.senderUser = senderUser;
    }

    public AppUser getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(AppUser receiverUser) {
        this.receiverUser = receiverUser;
    }
}
