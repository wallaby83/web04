package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet
{
    private static final long serialVersionUID = -5747267822399876261L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInForm.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

            String email = req.getParameter("email");
            String password = req.getParameter("password");
            Member member = memberDao.exist(email, password);

            if(member != null)
            {
                HttpSession session = req.getSession();
                session.setAttribute("member", member);

                resp.sendRedirect("../member/list");
            }
            else
            {
                RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInFail.jsp");
                rd.forward(req, resp);
            }
        }
        catch(Exception e)
        {
            throw new ServletException(e);
        }
        finally
        {
        }
    }
}
