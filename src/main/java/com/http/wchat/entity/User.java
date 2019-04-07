package com.http.wchat.entity;


import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Setter
    @Expose
    private String login;
    @Setter
    private String password;
    @OneToMany(mappedBy = "userFrom")
    private Set<Message> messages = new HashSet<>();
//    private List<Message> messages = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}' + "\n";
    }
}
