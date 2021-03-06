package com.http.wchat.controller.command.impl;

import com.http.wchat.controller.command.Command;
import com.http.wchat.controller.command.tool.Forward;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class GoToMainPage implements Command {

    private ViewAllUsers viewAllUsers;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        viewAllUsers.execute(request, response);
        Forward.forwardToMainPage(request, response);
    }
}
