package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

public class MemberUpdateController implements Controller, DataBinding
{
    MySqlMemberDao memberDao;

    public MemberUpdateController setMemberDao(MySqlMemberDao memberDao)
    {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders()
    {
        return new Object[] {"no", Integer.class, "member", spms.vo.Member.class};
    }



    @Override
    public String execute(Map<String, Object> model) throws Exception
    {
        if(((Member)model.get("member")).getEmail() == null)
        {
            Integer no = (Integer)model.get("no");
            Member member = memberDao.selectOne(no);

            model.put("member", member);

            return "/member/MemberUpdateForm.jsp";
        }
        else
        {
            Member member = (Member)model.get("member");

            memberDao.update(member);

            return "redirect:list.do";
        }
    }
}
