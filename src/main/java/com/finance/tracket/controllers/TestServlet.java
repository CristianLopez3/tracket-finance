package com.finance.tracket.controllers;

import com.finance.tracket.models.User;
import com.finance.tracket.persistence.MongoDBConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    private MongoDBConnector mongoDBConnector;

    @Override
    public void init(){
        mongoDBConnector = new MongoDBConnector("mongodb://localhost:27017", "test_db");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        List<User> users = new ArrayList<>();

        for (Document doc : mongoDBConnector.getCollection("users").find()) {
            User user = new User(doc);
            users.add(user);
            System.out.println("Retrieved user: " + user.getName() + ", " + user.getPosition());
        }

        HttpSession session = request.getSession();
        session.setAttribute("users", users);

        for (User user : users) {
            System.out.println("Name: " + user.getName() + ", Position: " + user.getPosition());
        }
        response.sendRedirect("show_users.jsp");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String position = request.getParameter("position");

        Document newUser = new Document("name", name)
                .append("position", position);

        System.out.println(
                mongoDBConnector.getCollection("users").insertOne(newUser).getInsertedId()
        );
        System.out.println("Name: " + name + ", Position: " + position);

    }


    @Override
    public void destroy() {
        mongoDBConnector.close();
    }

}
