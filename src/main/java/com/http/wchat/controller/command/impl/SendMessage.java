package com.http.wchat.controller.command.impl;

import com.http.wchat.controller.command.Command;
import com.http.wchat.controller.command.tool.Forward;
import com.http.wchat.entity.User;
import com.http.wchat.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@AllArgsConstructor
public class SendMessage implements Command {
    private ClientService service;

    @SuppressWarnings("Duplicates")
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        long companionID = Long.parseLong(request.getParameter("userToTalk"));
        service.sendMessage(user.getId(), companionID, request.getParameter("messageText"));
    }
}
