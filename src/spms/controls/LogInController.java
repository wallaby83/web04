package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.dao.MySqlMemberDao;
import spms.vo.Member;

public class LogInController implements Controller
{
    MySqlMemberDao memberDao;

    public LogInController setMemberDao(MySqlMemberDao memberDao)
    {
        this.memberDao = memberDao;
        return this;
    }


    @Override
    public String execute(Map<String, Object> model) throws Exception
    {
        if (model.get("loginInfo") == null)
        {
            return "/auth/LogInForm.jsp";
        }
        else
        {
            //MemberDao memberDao = (MemberDao)model.get("memberDao");
            Member loginInfo = (Member)model.get("loginInfo");

            Member member = memberDao.exist(loginInfo.getEmail(), loginInfo.getPassword());

            if(member != null)
            {
                HttpSession session = (HttpSession)model.get("session");
                session.setAttribute("member", member);

                return "redirect:../member/list.do";
            }
            else
            {
                return "/auth/LogInFail.jsp";
            }
        }
    }
}
