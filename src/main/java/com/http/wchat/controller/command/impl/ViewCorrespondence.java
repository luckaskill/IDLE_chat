package com.http.wchat.controller.command.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.http.wchat.controller.command.Command;
import com.http.wchat.controller.command.tool.Forward;
import com.http.wchat.entity.Message;
import com.http.wchat.entity.User;
import com.http.wchat.service.ClientService;
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
public class ViewCorrespondence implements Command {
    private ClientService service;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, NullPointerException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        long id = user.getId();
        long companionID = Long.parseLong(request.getParameter("userToTalk"));
        List<Message> correspondence = service.viewTalk(id, companionID);
        session.setAttribute("companionID", companionID);
        session.setAttribute("lastMsgID", correspondence.get(correspondence.size() - 1).getId());
        session.setAttribute("firstPrintedMsgID", correspondence.get(0).getId());

        response.setContentType("application/json");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String jsonResponse = gson.toJson(correspondence);
        response.getWriter().write(jsonResponse);
    }
}
