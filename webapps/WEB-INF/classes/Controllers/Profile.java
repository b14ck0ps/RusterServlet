package Controllers;

import Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var user = DBservices.DatabaseOperations.getUserByUsername((String) req.getSession().getAttribute("user"));
        req.setAttribute("user", user);
        if (req.getParameter("edit") != null) {
            req.getRequestDispatcher("ProfileEdit.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("Profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var username = req.getParameter("username");
        var email = req.getParameter("email");
        var password = req.getParameter("password");

        //validate
        if (username == null || username.isEmpty() || email == null || email.isEmpty()) {
            req.setAttribute("error", "All fields are required");
            req.getRequestDispatcher("ProfileEdit.jsp").forward(req, resp);
            return;
        }
        if(password == null || password.isEmpty()){
            password = DBservices.DatabaseOperations.getUserByUsername((String) req.getSession().getAttribute("user")).getPassword();
        }
        //update
        var user = DBservices.DatabaseOperations.getUserByUsername((String) req.getSession().getAttribute("user"));
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        if(DBservices.DatabaseOperations.updateUser(user) == 1){
            resp.sendRedirect("/Profile");
        }else{
            req.setAttribute("error", "Something went wrong");
            req.getRequestDispatcher("ProfileEdit.jsp").forward(req, resp);
        }
    }
}
