package com.http.wchat.controller.command.impl;

import com.http.wchat.controller.command.Command;
import com.http.wchat.entity.User;
import com.http.wchat.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
@AllArgsConstructor
public class ViewAllUsers implements Command {
    private ClientService service;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        ArrayList<User> users = (ArrayList<User>) service.viewAllUsers();
        request.getSession(false).setAttribute("allUsers", users);
    }
}
