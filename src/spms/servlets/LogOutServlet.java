package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth/logout")
public class LogOutServlet extends HttpServlet
{
    private static final long serialVersionUID = 8330595296983774478L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        session.invalidate();

        req.setAttribute("viewUrl", "redirect:login.do");
    }
}
