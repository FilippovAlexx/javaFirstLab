package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.FunctionJDBC;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req,resp);

        /*String authentication = req.getParameter("authentication");
        String message = Optional.ofNullable(authentication)
                .map(x->"Auth error").orElse("");*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.equals(password)){
            HttpSession session = req.getSession(true);
            session.setAttribute("login", login);
            if (!FunctionJDBC.userExistsByLogin(login)) {
                FunctionJDBC.addUser(login, password);
                System.out.println("Person with login:" + login + "\n" + "with password: " + password + "\nis created");
            }
            else
            {
                System.out.println("Person is already exists");
            }
            resp.sendRedirect(req.getContextPath() + "/data");
        }else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
