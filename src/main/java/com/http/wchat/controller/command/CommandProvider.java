package com.http.wchat.controller.command;

import com.http.wchat.controller.command.impl.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandProvider {
    public enum CommandName {
        authorization, goToStartPage, viewCorrespondence, sendMessage,
        loadNewMessages, logOut, goToMainPage, loadMessages
    }

    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider(ApplicationContext context) {
        commands.put(CommandName.authorization, context.getBean(Authorization.class));
        commands.put(CommandName.goToStartPage, context.getBean(GoToStartPage.class));
        commands.put(CommandName.viewCorrespondence, context.getBean(ViewCorrespondence.class));
        commands.put(CommandName.sendMessage, context.getBean(SendMessage.class));
        commands.put(CommandName.loadNewMessages, context.getBean(LoadNewMessages.class));
        commands.put(CommandName.logOut, context.getBean(LogOut.class));
        commands.put(CommandName.goToMainPage, context.getBean(GoToMainPage.class));
        commands.put(CommandName.loadMessages, context.getBean(LoadMessages.class));
    }

    public Command getCommand(CommandName commandName) {
        return commands.get(commandName);
    }
}
