/**
 * Created by Sergey Buglak
 */

package pvt.by.controller;

import pvt.by.command.util.CommandHandler;
import pvt.by.command.util.RequestHandler;
import pvt.by.command.util.WebConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main application controller
 */
public class Controller extends HttpServlet {
    @Override
    public void init() throws ServletException {
    }

    /*
     * Implements a Command pattern to rule requests
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandHandler.handleCommand(req.getParameter(WebConstants.DO)).execute(req, resp);
        RequestHandler.handleRequest(req, resp);
    }
}