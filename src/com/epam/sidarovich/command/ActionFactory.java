package com.epam.sidarovich.command;


import org.apache.log4j.Logger;
import com.epam.sidarovich.manager.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ActionFactory {

    private static final Logger LOG = Logger.getLogger(ActionCommand.class);

    /**
     * Define executing command
     * @param request
     * @return
     */
    public ActionCommand defineCommand(HttpServletRequest request) {

       ActionCommand currentCommand = new EmptyCommand();
       String action = request.getParameter("command");
        HttpSession session = request.getSession();
        SessionLocaleManager sessionLocaleManager = new SessionLocaleManager();
        Locale locale = sessionLocaleManager.receiveLocale(session);

       if (action == null || action.isEmpty()) {
       }
       try {
           CommandEnum currentEnum = CommandEnum.valueOf(action.replace("-", "_").toUpperCase());
           currentCommand = currentEnum.getCurrentCommand();
           LOG.info(action);
       } catch (IllegalArgumentException e) {
           MessageManager messageManager = new MessageManager();
           request.setAttribute("wrongAction", action + messageManager.getProperty("message.wrongaction",locale));
       }
       return currentCommand;
   }
}