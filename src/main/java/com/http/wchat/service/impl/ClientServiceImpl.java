package com.http.wchat.service.impl;

import com.http.wchat.dao.impl.SQLUserDAO;
import com.http.wchat.entity.Message;
import com.http.wchat.entity.User;
import com.http.wchat.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

@Component
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private SQLUserDAO userDAO;

    @Override
    public User authorization(String login, String password) {
        return userDAO.authorization(login, password);
    }

    @Override
    public List<Message> viewTalk(long speaker1ID, long speaker2ID) {
        return sortMessagesByDate(userDAO.viewTalk(speaker1ID, speaker2ID));
    }

    @Override
    public void sendMessage(long senderID, long recipientID, String text) {
        Message message = new Message();
        message.setText(text);
        message.setUserFrom(findUser(senderID));
        message.setRecipientID(recipientID);
        message.setTime(userDAO.enquireTime());
        userDAO.sendMessage(senderID, recipientID, message);
    }

    @Override
    public List<Message> loadNewMessages(long speaker1ID, long speaker2ID, long lastMsgID) {
        return userDAO.loadNewMessages(speaker1ID, speaker2ID, lastMsgID);
    }

    @Override
    public List<Message> loadMessages(long speaker1ID, long speaker2ID, long lastPrintedMsgID) {
        return sortMessagesByDate(userDAO.loadMessages(speaker1ID, speaker2ID, lastPrintedMsgID));
    }

    @Override
    public List<User> viewAllUsers() {
        return userDAO.viewAllUsers();
    }

    @Override
    public User findUser(long id) {
        return userDAO.findUser(id);
    }

    @Override
    public Timestamp enquireTime() {
        return userDAO.enquireTime();
    }

    private List<Message> sortMessagesByDate(List<Message> messages) {
        Comparator<Message> comparator = Comparator.comparing(Message::getTime);
        messages.sort(comparator);
        return messages;
    }

    private List<User> sortUsersByAlphabet(List<User> users){
        Comparator<User> comparator = Comparator.comparing(User::getLogin);
        users.sort(comparator);
        return users;
    }
}
