package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

public class MemberAddController implements Controller, DataBinding
{
    MySqlMemberDao memberDao;

    public MemberAddController setMemberDao(MySqlMemberDao memberDao)
    {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders()
    {
        return new Object[] { "member", spms.vo.Member.class };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception
    {
        Member member = (Member)model.get("member");

        if(member.getEmail() == null)
        {
            return "/member/MemberForm.jsp";
        }
        else
        {
            //MemberDao memberDao = (MemberDao)model.get("memberDao");
            memberDao.insert(member);

            return "redirect:list.do";
        }
    }
}
