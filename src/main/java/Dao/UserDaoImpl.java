package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.Dbutils.*;

import pojos.User;

public class UserDaoImpl implements UserDao {
	private Connection cn;
	private PreparedStatement pst1,pst2,pst3,pst4;
	
	
	
	public UserDaoImpl() throws SQLException {
		this.cn = getConnection();
		pst1=cn.prepareStatement("select * from users where email=? and password=?");
		pst2=cn.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
		pst3=cn.prepareStatement("update users set status=? where id=?");
		pst4=cn.prepareStatement("delete from user where id=?");
	}

	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, password);
		
		try(ResultSet rs=pst1.executeQuery()){
			User u = null;
			if(rs.next()) {
				 u=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getBoolean(7),rs.getString(8));
				 
			}
			return u;
		}
		
	}

	@Override
	public String registerVoter(User user) throws SQLException {
		pst2.setString(1,user.getFname());
		pst2.setString(2,user.getLname());
		pst2.setString(3,user.getEmail());
		pst2.setString(4,user.getPass());
		pst2.setDate(5,user.getDob());
		pst2.setBoolean(6, false);
		pst2.setString(7,"voter");
		if(1==pst2.executeUpdate())
		return "voter registerd !!!!";
		else
			return "registration failed !!!";
	}

	@Override
	public String updateVoterStatus(int voterId) throws SQLException {
		pst3.setBoolean(1, true);
		pst3.setInt(2,voterId);
		if(1==pst3.executeUpdate())
		return "voter status updated";
		else
			return "failed to update status";
	}

	@Override
	public String deleteUser(int id) throws SQLException {
		pst4.setInt(1, id);
		if(1==pst4.executeUpdate())
		return "user delelted";
		else
			return "failed to delete";
	}

}
