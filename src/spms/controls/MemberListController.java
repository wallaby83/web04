package spms.controls;

import java.util.Map;

import spms.dao.MySqlMemberDao;

public class MemberListController implements Controller
{
    MySqlMemberDao memberDao;

    public MemberListController setMemberDao(MySqlMemberDao memberDao)
    {
        this.memberDao = memberDao;
        return this;
    }


    @Override
    public String execute(Map<String, Object> model) throws Exception
    {
        //MemberDao memberDao = (MemberDao)model.get("memberDao");
        model.put("members", memberDao.selectList());

        return "/member/MemberList.jsp";
    }
}