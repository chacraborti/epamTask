package com.epam.sidarovich.command;


import com.epam.sidarovich.manager.PathPageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SelectLanguageCommand implements ActionCommand {

    private static final String LANGUAGE_PARAM = "language";
    private static final String LOCALE_ATTR = "Locale";

    /**
     * Select language, go to login page
     *
     * @param request
     * @return
     */
    @Override
    public String execute(HttpServletRequest request) {
        PathPageManager pathPageManager = new PathPageManager();
        HttpSession session = request.getSession();
        if (request.getParameter(LANGUAGE_PARAM).equals("Ru")) {
            session.setAttribute(LOCALE_ATTR, "ru");
        } else {
            session.setAttribute(LOCALE_ATTR, "en");
        }
        return pathPageManager.getProperty("path.page.login");
    }
}
