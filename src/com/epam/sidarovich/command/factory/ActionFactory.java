package com.epam.sidarovich.command.factory;


import com.epam.sidarovich.command.ActionCommand;
import com.epam.sidarovich.command.CommandEnum;
import com.epam.sidarovich.command.EmptyCommand;
import com.epam.sidarovich.manager.MessageManager;
import com.epam.sidarovich.manager.SessionLocaleManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ActionFactory {

    private static final Logger LOG = Logger.getLogger(ActionCommand.class);
    private static final String COMMAND_PARAM = "command";
    private static final String WRONG_ACTION_ATTR = "wrongAction";

    /**
     * Define executing command
     *
     * @param request
     * @return
     */
    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand currentCommand = new EmptyCommand();
        String action = request.getParameter(COMMAND_PARAM);
        HttpSession session = request.getSession();
        SessionLocaleManager sessionLocaleManager = new SessionLocaleManager();
        Locale locale = sessionLocaleManager.receiveLocale(session);
        MessageManager messageManager = new MessageManager();

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.replace("-", "_").toUpperCase());
            currentCommand = currentEnum.getCurrentCommand();
            LOG.info(action);
        } catch (IllegalArgumentException e) {
            request.setAttribute(WRONG_ACTION_ATTR, action + messageManager.getProperty("message.wrongaction", locale));
        }
        return currentCommand;
    }
}