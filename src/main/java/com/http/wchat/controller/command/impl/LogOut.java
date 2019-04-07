package com.http.wchat.controller.command.impl;


import com.http.wchat.controller.command.Command;
import com.http.wchat.controller.command.tool.Forward;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogOut implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(false).invalidate();
        Forward.forwardToStartPage(request, response);
    }
}
