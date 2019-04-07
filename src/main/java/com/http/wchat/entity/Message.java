package com.http.wchat.entity;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "messages")
@Getter
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private long id;
    @Setter
    @Expose
    private String text;
    @Setter
    @ManyToOne
    @Expose
    @JoinColumn(name = "from_user_id")
    private User userFrom;
    @Setter
    @Column(name = "to_user_id")
    private long recipientID;
    @Expose
    @Setter
    private Timestamp time;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", userFromID=" + userFrom.getId() +
                ", userToID=" + recipientID +
                ", time=" + time +
                "}\n";
    }
}
