package com.http.wchat.controller.command.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.http.wchat.controller.command.Command;
import com.http.wchat.entity.Message;
import com.http.wchat.entity.User;
import com.http.wchat.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Component
@AllArgsConstructor
public class LoadNewMessages implements Command {
    private ClientServiceImpl service;

    @SuppressWarnings("Duplicates")
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        long id = user.getId();
        long companionID = Long.parseLong(request.getParameter("userToTalk"));

        List<Message> newMsgs = service.loadNewMessages(id, companionID, (Long) session.getAttribute("lastMsgID"));
        if (newMsgs.isEmpty()) {
            return;
        }
        session.setAttribute("lastMsgID", newMsgs.get(newMsgs.size() - 1).getId());
        response.setContentType("application/json");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String jsonResponse = gson.toJson(newMsgs);
        response.getWriter().write(jsonResponse);
    }
}
