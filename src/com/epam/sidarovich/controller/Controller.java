package com.epam.sidarovich.controller;


import com.epam.sidarovich.command.ActionCommand;
import com.epam.sidarovich.command.factory.ActionFactory;
import com.epam.sidarovich.connection.ConnectionPool;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.manager.PathPageManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ilona on 01.04.15.
 */

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    public void init() {
        new DOMConfigurator().configure(getServletContext().getRealPath("/data/log4j.xml"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process request from jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        String page = null;
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            LOG.error(e);
        }
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            PathPageManager pathPageManager = new PathPageManager();
            request.getRequestDispatcher(pathPageManager.getProperty("path.page.error")).forward(request, response);
        }
    }

    /**
     * Destroy servlet
     */
    public void destroy() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.shutDownConnections();

    }
}


