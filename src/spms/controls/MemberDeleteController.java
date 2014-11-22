package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;

public class MemberDeleteController implements Controller, DataBinding
{
    MySqlMemberDao memberDao;

    public MemberDeleteController setMemberDao(MySqlMemberDao memberDao)
    {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders()
    {
        return new Object[] {"no", Integer.class};
    }


    @Override
    public String execute(Map<String, Object> model) throws Exception
    {
        Integer no = (Integer)model.get("no");

        memberDao.delete(no);

        return "redirect:list.do";
    }

}
