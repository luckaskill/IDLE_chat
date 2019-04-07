package com.http.wchat.controller;

import com.http.wchat.controller.command.Command;
import com.http.wchat.controller.command.CommandProvider;
import com.http.wchat.controller.command.tool.Forward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final String PARAMETER_COMMAND = "command";
    @Autowired
    private ApplicationContext context = new AnnotationConfigApplicationContext("com.http.wchat");
    private CommandProvider provider = context.getBean(CommandProvider.class);

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String commandName = request.getParameter(PARAMETER_COMMAND);
        Command command;
        try {
            System.out.println("O, HI MARK");
            System.out.println(commandName);
            command = provider.getCommand(CommandProvider.CommandName.valueOf(commandName));
            command.execute(request, response);
        } catch (NullPointerException e) {
//            Forward.redirect(response, "controller?command=viewCorrespondence");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
