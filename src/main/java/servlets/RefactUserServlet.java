package servlets;

import entity.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FunctionJDBC;

import java.io.IOException;

@WebServlet("/user")
public class RefactUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =req.getParameter("id");
        System.out.println(id);
        try {
            Person person = FunctionJDBC.getUserData(Integer.parseInt(id));
            req.setAttribute("login", person.getLogin());
            req.setAttribute("password", person.getPassword());
            req.getRequestDispatcher("user.jsp").forward(req,resp);

            System.out.println(person.getLogin() + person.getPassword());
            System.out.println(req.getAttribute("login"));
            System.out.println(req.getAttribute("password"));

            //Person person = new Person(id, userData[]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        System.out.println(action + " " + id + " " + login + " " + password);

        if (action != null) {
            if (action.equals("change")) {
                FunctionJDBC.changeUser(Integer.parseInt(id), login, password);
                resp.sendRedirect(req.getContextPath() + "/data");
            } else if (action.equals("delete")) {
                FunctionJDBC.deleteUser(Integer.parseInt(id));
                resp.sendRedirect(req.getContextPath() + "/data");
            }
    }
    }
}
