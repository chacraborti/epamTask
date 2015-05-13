package com.epam.sidarovich.command;


import com.epam.sidarovich.manager.*;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    /**
     * Logout
     * @param request
     * @return
     */
    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().invalidate();
        PathPageManager pathPageManager =new PathPageManager();
        return pathPageManager.getProperty("path.page.index");
    }
}