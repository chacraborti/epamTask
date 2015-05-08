package com.epam.sidarovich.command.command;


import manager.MessageManager;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final Logger LOG = Logger.getLogger(ActionCommand.class);

    public ActionCommand defineCommand(HttpServletRequest request) {

       ActionCommand currentCommand = new EmptyCommand();
       String action = request.getParameter("command");

       if (action == null || action.isEmpty()) {
       }
       try {
           CommandEnum currentEnum = CommandEnum.valueOf(action.replace("-", "_").toUpperCase());
           currentCommand = currentEnum.getCurrentCommand();
           LOG.info(action);
       } catch (IllegalArgumentException e) {
           MessageManager messageManager = new MessageManager();
           request.setAttribute("wrongAction", action + messageManager.getProperty("message.wrongaction"));
       }
       return currentCommand;
   }
}