package com.http.wchat.dao.impl;

import com.http.wchat.dao.HibernateSessionFactoryUtil;
import com.http.wchat.dao.patterns.UserDAO;
import com.http.wchat.entity.Message;
import com.http.wchat.entity.User;
import lombok.Cleanup;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class SQLUserDAO implements UserDAO {
    @Override
    public User authorization(String login, String password) {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (User) session.createQuery(
                "from User where login='" + login + "' and password='" + password + "'")
                .uniqueResult();
    }

    @Override
    public List<Message> viewTalk(long speaker1ID, long speaker2ID) {
        @Cleanup
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery(
        "from Message where recipientID='" + speaker2ID + "' and userFrom.id='" + speaker1ID + "'" + " " +
                "OR recipientID='" + speaker1ID + "' and userFrom.id='" + speaker2ID + "' order by time desc", Message.class)
                .setMaxResults(15)
                .list();
    }

    @Override
    public void sendMessage(long senderID, long recipientID, Message message) {
        @Cleanup
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
    }

    @Override
    public List<Message> loadNewMessages(long speaker1ID, long speaker2ID, long lastMsgID) {
        @Cleanup
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("from Message where " +
                "recipientID='" + speaker2ID + "' and userFrom.id='" + speaker1ID + "' and id >'" + lastMsgID + "'" +
                "OR recipientID='" + speaker1ID + "' and userFrom.id='" + speaker2ID + "' and id>'" + lastMsgID + "'", Message.class).list();
    }

    @Override
    public List<Message> loadMessages(long speaker1ID, long speaker2ID, long lastPrintedMsgID) {
        @Cleanup
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("from Message where " +
                "recipientID='" + speaker2ID + "' and userFrom.id='" + speaker1ID + "' and id <'" + lastPrintedMsgID + "'" +
                "OR recipientID='" + speaker1ID + "' and userFrom.id='" + speaker2ID + "' and id<'" + lastPrintedMsgID + "'  order by time desc",
                Message.class)
                .setMaxResults(20).list();
    }

    @Override
    public List<User> viewAllUsers() {
        @Cleanup
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("from User", User.class).list();
    }

    @Override
    public User findUser(long id) {
        @Cleanup
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.get(User.class, id);
    }

    @Override
    public Timestamp enquireTime() {
        @Cleanup
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (Timestamp) session.createSQLQuery("SELECT NOW()").uniqueResult();
    }
}
