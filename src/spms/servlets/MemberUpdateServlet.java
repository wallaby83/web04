package spms.servlets;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;


@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

            int no = Integer.parseInt(request.getParameter("no"));
            Member member = memberDao.selectOne(no);

            request.setAttribute("member", member);
            RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdateForm.jsp");
            rd.forward(request, response);

        }
        catch(Exception e)
        {
            //throw new ServletException(e);
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);
        }
        finally
        {
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

            Member member = new Member();
            member.setEmail(request.getParameter("email"));
            member.setName(request.getParameter("name"));
            member.setNo(Integer.parseInt(request.getParameter("no")));

            memberDao.update(member);

            response.sendRedirect("list");

        }
        catch(Exception e)
        {
            //throw new ServletException(e);
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);

        }
        finally
        {
        }
    }
}
