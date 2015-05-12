package com.epam.sidarovich.command;



import com.epam.sidarovich.manager.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SelectLanguageCommand implements ActionCommand{
    /**
     * Select language, go to login page
     * @param request
     * @return
     */
    @Override
    public String execute(HttpServletRequest request) {


        HttpSession session = request.getSession();
        if(request.getParameter("language").equals("Ru")){
            session.setAttribute("Locale", "ru");
        }
        else {
            session.setAttribute("Locale", "en");
        }
        ConfigurationManager configurationManager=new ConfigurationManager();
        return configurationManager.getProperty("path.page.login");
    }
}
