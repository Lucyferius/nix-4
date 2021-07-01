package com.alevel.hometask;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "app-servlet", urlPatterns = {"/app"})
public class AppServlet extends HttpServlet {
    private final Set<String> addressSet = ConcurrentHashMap.newKeySet();
    private static final Logger log = LoggerFactory.getLogger(AppServlet.class);

    @Override
    public void init() {
        log.info("App Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter responseBody = resp.getWriter();
        addressSet.add(req.getRemoteHost() + " :: " + req.getHeader("User-Agent"));

        responseBody.println("<h1 align=center>Clients ip and user-agent</h1>");
        responseBody.println("<ul>");
        for (String address: addressSet ) {
            responseBody.println(" <li>" + "<b>" + address + "</b>" + "</li>");
        }
        responseBody.println("</ul>");

    }

    @Override
    public void destroy() {
        log.info("Sample Servlet destroyed");
    }
}
