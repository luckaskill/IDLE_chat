package com.http.wchat.controller.command.tool;

import com.http.wchat.controller.command.impl.ViewAllUsers;
import lombok.AllArgsConstructor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class Forward {
    private static final String MAIN_PAGE = "WEB-INF/jsp/userMain.jsp";
    private static final String START_PAGE = "WEB-INF/jsp/start_page.jsp";


    public static void forwardToMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(request, response);
    }

    public static void forwardToStartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(START_PAGE);
        dispatcher.forward(request, response);
    }

    public static void redirect(HttpServletResponse response, String command) throws IOException {
        response.sendRedirect(command);
    }
}
