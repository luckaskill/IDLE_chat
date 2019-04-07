package com.http.wchat.dao.patterns;

import com.http.wchat.entity.Message;
import com.http.wchat.entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserDAO {
    User authorization(String login, String password);
    List<Message> viewTalk(long speaker1ID, long speaker2ID);
    void sendMessage(long senderID, long recipientID, Message message);
    List<Message> loadNewMessages(long speaker1ID, long speaker2ID, long lastMsgID);
    List<Message> loadMessages(long speaker1ID, long speaker2ID, long lastPrintedMsgID);
    List<User> viewAllUsers();
    User findUser(long id);
    Timestamp enquireTime();
}
