package com.http.wchat.service.impl;

import com.http.wchat.dao.HibernateSessionFactoryUtil;
import com.http.wchat.dao.impl.SQLUserDAO;
import com.http.wchat.entity.Message;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class ClientServiceImplTest {
    private static ClientServiceImpl service;
    private static int speaker1ID;
    private static int speaker2ID;

    @BeforeAll
    static void setUp() {
        service = new ClientServiceImpl(new SQLUserDAO());
        speaker1ID = 1;
        speaker2ID = 2;
        @Cleanup
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    @Test
    void authorization() {
        System.out.println(service.authorization("admin","admin"));
        System.out.println(service.authorization("adminww","admin"));
    }

    @Test
    void viewTalk() {
        List<Message> messages = service.viewTalk(speaker1ID, speaker2ID);
        System.out.println(messages);
        for (int i = 0; i < 4; i++) {
            try {
                System.out.println((messages = loadMessages(messages.get(0).getId())));
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
    }

    List<Message> loadMessages(long lastMsgID) {
        return service.loadMessages(speaker1ID, speaker2ID, lastMsgID);
    }

    @Test
    void sendMessage() {
//        service.sendMessage(speaker1ID, speaker2ID, "JUNIT");
    }

    void loadNewMessages() {
    }

    void loadMessages() {
    }

    @Test
    void viewAllUsers() {
        System.out.println(service.viewAllUsers());
    }
}