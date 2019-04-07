package com.http.wchat.controller.command.impl;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.http.wchat.controller.command.Command;
import com.http.wchat.controller.command.tool.Forward;
import com.http.wchat.entity.User;
import com.http.wchat.service.ClientService;
import com.http.wchat.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class Authorization implements Command {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    private ClientService service;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = service.authorization(request.getParameter(PARAMETER_LOGIN),
                request.getParameter(PARAMETER_PASSWORD));
        if (user == null) {
            request.setAttribute("error", "Wrong data");
            Forward.forwardToStartPage(request, response);
            return;
        }
        request.getSession(true).setAttribute("user", user);
        Forward.redirect(response, "controller?command=goToMainPage");
    }
}
