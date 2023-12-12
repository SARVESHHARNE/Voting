package Dao;

import java.sql.SQLException;

import pojos.User;

public interface UserDao {
	User authenticateUser(String email,String password) throws SQLException;
	String registerVoter(User user) throws SQLException;
	String updateVoterStatus(int voterId) throws SQLException;
	String deleteUser(int id)throws SQLException; 
}
