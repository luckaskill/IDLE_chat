package com.http.wchat.service;

import com.http.wchat.entity.Message;
import com.http.wchat.entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface ClientService {
    User authorization(String login, String password);
    List<Message> viewTalk(long speaker1ID, long speaker2ID);
    void sendMessage(long senderID, long recipientID, String text);
    List<Message> loadNewMessages(long speaker1ID, long speaker2ID, long lastMsgID);
    List<Message> loadMessages(long speaker1ID, long speaker2ID, long lastPrintedMsgID);
    List<User> viewAllUsers();
    User findUser(long id);
    Timestamp enquireTime();
}
