package ru.kpfu.itis.servlets;

import ru.kpfu.itis.utilities.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ConfigureServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        Database.getInstance();
    }
}
