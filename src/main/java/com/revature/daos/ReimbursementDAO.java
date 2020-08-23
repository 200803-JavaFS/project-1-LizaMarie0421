package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAO implements IReimbursementDAO{

	@Override
	public List<Reimbursement> findAll() {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement;";
			Statement statement = conn.createStatement();
			List<Reimbursement> list = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				//using all arg constructor instead of setting
				//(reimb_amount, reimb_submitted, reimb_resolved, reimb_description, 
				//reimb_author,	reim_resolver, reim_status_id,	reim_type_id)
				Reimbursement r = new Reimbursement(result.getInt("reimb_id"),
						result.getDouble("reimb_amount"),
						result.getString("reimb_submitted"),
						result.getString("reimb_resolved"),
						result.getString("reimb_description"),
						result.getInt("reimb_author"),
						result.getInt("reim_resolver"),
						result.getInt("reim_status_id"),
						result.getInt("reim_type_id"));
				
				list.add(r);
			}
			
			return list;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reimbursement findById(int id) {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement WHERE reimb_id =" +id+";";
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				Reimbursement r = new Reimbursement(result.getInt("reimb_id"),
						result.getDouble("reimb_amount"),
						result.getString("reimb_submitted"),
						result.getString("reimb_resolved"),
						result.getString("reimb_description"),
						result.getInt("reimb_author"),
						result.getInt("reim_resolver"),
						result.getInt("reim_status_id"),
						result.getInt("reim_type_id"));
				
				return r;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addReimbursement(Reimbursement r) {
		try(Connection conn= ConnectionUtil.getConnection()){
			//prepared staement indicates use of ??
			String sql = "INSERT INTO reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, "
					+ "reimb_author, reim_resolver, reim_status_id,	reim_type_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?,?, ?, ?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setDouble(++index, r.getAmount());
			statement.setString(++index, r.getSubmitted());
			statement.setString(++index, r.getResolved() );
			statement.setString(++index, r.getDescription());
			statement.setInt(++index, r.getAuthor());
			statement.setInt(++index, r.getResolver());
			statement.setInt(++index, r.getStatusId());
			statement.setInt(++index, r.getTypeId());
			
			
			 statement.execute();
			//execute returns true only if it brings back data 
			 return true;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateReimbursement(Reimbursement r) {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "UPDATE reimbursement SET reimb_amount= ?, reimb_submitted= ?,"
					+ " reimb_resolved= ?, reimb_description= ?, reimb_author= ?, reim_resolver= ? "
					+ "reim_status_id=?,reim_type_id=? WHERE reimb_id=?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setDouble(++index, r.getAmount());
			statement.setString(++index, r.getSubmitted());
			statement.setString(++index, r.getResolved() );
			statement.setString(++index, r.getDescription());
			statement.setInt(++index, r.getAuthor());
			statement.setInt(++index, r.getResolver());
			statement.setInt(++index, r.getStatusId());
			statement.setInt(++index, r.getTypeId());
			statement.execute();
			return true;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Reimbursement> findByUser(int userId) {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement WHERE reimb_author =" +userId+";";
			Statement statement = conn.createStatement();
			List<Reimbursement> list = new ArrayList<>();
			ResultSet result = statement.executeQuery(sql);
						
			while (result.next()){
				Reimbursement r = new Reimbursement(result.getInt("reimb_id"),
						result.getDouble("reimb_amount"),
						result.getString("reimb_submitted"),
						result.getString("reimb_resolved"),
						result.getString("reimb_description"),
						result.getInt("reimb_author"),
						result.getInt("reim_resolver"),
						result.getInt("reim_status_id"),
						result.getInt("reim_type_id"));
				
				list.add(r);
			}
			return list;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public List<Reimbursement> findByStatus(String status) {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement"
					+ "JOIN reimb_status"
					+ "ON r.reim_status_id =rs.reimb_status_id"
					+ "WHERE reimb_status= ?;";			
			List<Reimbursement> list = new ArrayList<>();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, status);
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()){
				Reimbursement r = new Reimbursement(result.getInt("reimb_id"),
						result.getDouble("reimb_amount"),
						result.getString("reimb_submitted"),
						result.getString("reimb_resolved"),
						result.getString("reimb_description"),
						result.getInt("reimb_author"),
						result.getInt("reim_resolver"),
						result.getInt("reim_status_id"),
						result.getInt("reim_type_id"));
				
				list.add(r);
			}			
			return list;		
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return Collections.emptyList();
	}

}
