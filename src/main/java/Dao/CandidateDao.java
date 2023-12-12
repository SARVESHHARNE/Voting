package Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pojos.Candidate;

public interface CandidateDao {
	List<Candidate> getAll() throws SQLException;
	List<Candidate> getTopTwo()throws SQLException;
	Map<String, Integer> getPartyWise()throws SQLException;
	String updateVotes(int id) throws SQLException;
}
