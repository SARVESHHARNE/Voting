package Dao;

import java.sql.Connection;
import static utils.Dbutils.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojos.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	Connection cn;
	PreparedStatement pst1,pst2,pst3,pst4;
	

	public CandidateDaoImpl() throws SQLException {
		super();
		this.cn = getConnection();
		this.pst1 = cn.prepareStatement("select * from candidates");
		this.pst2 = cn.prepareStatement("Select * from candidates order by votes desc limit 2");
		this.pst3 = cn.prepareStatement("select party,sum(votes)votes from candidates group by party order by votes desc");
		this.pst4 = cn.prepareStatement("update candidates set votes=votes+1 where id=?");
	}

	@Override
	public List<Candidate> getAll() throws SQLException {
		List<Candidate> lst=new ArrayList<Candidate>();
		ResultSet rs = pst1.executeQuery();
		while(rs.next()) {
			lst.add(new Candidate(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		return lst;
	}

	@Override
	public List<Candidate> getTopTwo() throws SQLException {
		List<Candidate> lst=new ArrayList<Candidate>();
		ResultSet rs = pst2.executeQuery();
		while(rs.next()) {
			lst.add(new Candidate(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		return lst;
	}

	@Override
	public  Map<String,Integer> getPartyWise() throws SQLException {
		Map<String,Integer> map=new HashMap<String, Integer>();
		ResultSet rs = pst3.executeQuery();
		while(rs.next()) {
			map.put(rs.getString(1),rs.getInt(2));
		}
		return map;
	}
	
	@Override
	public  String updateVotes(int id) throws SQLException {
		pst4.setInt(1, id);
		pst4.executeUpdate();
		return "votes updted";
	}

}
