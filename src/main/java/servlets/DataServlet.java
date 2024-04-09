package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.FunctionJDBC;

import java.io.IOException;

@WebServlet("/data")
public class DataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session!=null){
            String login = (String) session.getAttribute("login");
            if (login != null){
                req.getRequestDispatcher("/data.jsp").forward(req,resp);
            }
            else {
                resp.sendRedirect(req.getContextPath()+"/logout");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        //req.setAttribute("id", id);
        if(!id.isEmpty() && FunctionJDBC.userExistsByID(Integer.parseInt(id))){
            System.out.println(id);
            //req.setAttribute("id", id);
            resp.sendRedirect(req.getContextPath() + "/user?id=" + id);
        }else {
            resp.sendRedirect(req.getContextPath() + "/data");
        }
        //req.getRequestDispatcher("user.jsp").forward(req,resp);
        /*String value = req.getParameter("name");
        System.out.println("444444");
        if (value.equals("Submit")){
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("22222222222");
            resp.sendRedirect(req.getContextPath() + "/user");
            System.out.println("Всё прошло");
        }else

            if (value.equals("Logout")){
                System.out.println("3333");
                resp.sendRedirect(req.getContextPath() + "/login");

            }*/
    }
}
