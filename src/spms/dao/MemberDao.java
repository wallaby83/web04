package spms.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.vo.Member;


public class MemberDao
{
    DataSource ds;

    public void setDataSource(DataSource ds)
    {
        this.ds = ds;
    }


    /**
     * selectList
     * @return
     * @throws Exception
     */
    public List<Member> selectList() throws Exception
    {
        System.out.println("selectList() 호출");

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            connection = ds.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT MNO, MNAME, EMAIL, CRE_DATE FROM MEMBERS ORDER BY MNO ASC");

            ArrayList<Member> members = new ArrayList<Member>();

            while(rs.next())
            {
                members.add(new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME")).setEmail(rs.getString("EMAIL")).setCreatedDate(rs.getDate("CRE_DATE")));
            }

            return members;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(stmt != null) stmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
    }


    /**
     * insert
     * @param member
     * @return
     * @throws Exception
     */
    public int insert(Member member) throws Exception
    {
        System.out.println("insert() 호출");

        Connection connection = null;
        PreparedStatement stmt = null;

        try
        {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)" + " VALUES (?,?,?,NOW(),NOW())");
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getPassword());
            stmt.setString(3, member.getName());
            return stmt.executeUpdate();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            try { if(stmt != null) stmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
    }


    /**
     * selectOne
     * @param no
     * @return
     * @throws Exception
     */
    public Member selectOne(int no) throws Exception
    {
        System.out.println("selectOne() 호출");

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try
        {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS WHERE MNO=?");
            stmt.setInt(1, no);
            rs = stmt.executeQuery();

            Member member = new Member();

            if(rs.next())
            {
                member.setNo(rs.getInt("MNO")).setName(rs.getString("MNAME")).setEmail(rs.getString("EMAIL")).setCreatedDate(rs.getDate("CRE_DATE"));
            }

            return member;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(stmt != null) stmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
    }


    /**
     * update
     * @param member
     * @return
     * @throws Exception
     */
    public int update(Member member) throws Exception
    {
        Connection connection = null;
        PreparedStatement stmt = null;

        try
        {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now() WHERE MNO=?");
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getName());
            stmt.setInt(3, member.getNo());

            return stmt.executeUpdate();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            try { if(stmt != null) stmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
    }


    /**
     * delete
     * @param no
     * @return
     * @throws Exception
     */
    public int delete(int no) throws Exception
    {
        Connection connection = null;
        PreparedStatement stmt = null;

        try
        {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("DELETE FROM MEMBERS WHERE MNO=?");
            stmt.setInt(1, no);
            return stmt.executeUpdate();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            try { if(stmt != null) stmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
    }


    /**
     * exist
     * @param email
     * @param password
     * @return
     * @throws Exception
     */
    public Member exist(String email, String password) throws Exception
    {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try
        {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("SELECT MNAME, EMAIL FROM MEMBERS WHERE EMAIL = ? AND PWD = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            Member member = null;

            if(rs.next())
            {
                member = new Member();
                member.setEmail(rs.getString("EMAIL"));
                member.setName(rs.getString("MNAME"));
            }

            return member;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(stmt != null) stmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
    }
}
