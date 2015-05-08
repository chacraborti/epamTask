package com.epam.sidarovich.command.command;



import com.epam.sidarovich.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SelectLanguageCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {


        HttpSession session = request.getSession();
        if(request.getParameter("language").equals("Ru")){
            session.setAttribute("Locale", "ru");
        }
        else {
            session.setAttribute("Locale", "en");
        }
        return ConfigurationManager.getProperty("path.page.login");
    }
}
