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

public class UserDAO implements IUserDAO{

	@Override
	public List<User> findAll() {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users;";
			Statement statement = conn.createStatement();
			List<User> list = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				//using all arg constructor instead of setting
				User u = new User(result.getInt("user_id"),
						result.getString("user_username"),
						result.getString("user_password"),
						result.getString("user_first"),
						result.getString("user_last"),
						result.getString("user_email"),
						result.getInt("user_role_id"));
				
				list.add(u);
			}
			
			return list;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findById(int id) {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users WHERE user_id =" +id+";";
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				User u = new User(result.getInt("user_id"),
						result.getString("user_username"),
						result.getString("user_password"),
						result.getString("user_first"),
						result.getString("user_last"),
						result.getString("user_email"),
						result.getInt("user_role_id"));
				
				return u;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addUser(User u) {
		try(Connection conn= ConnectionUtil.getConnection()){
			//prepared staement indicates use of ??
			String sql = "INSERT INTO users (user_username, user_password, user_first, user_last,"
					+ "user_email, user_role_id)"
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setString(++index, u.getUsername());
			statement.setString(++index, u.getPassword());
			statement.setString(++index, u.getFirst());
			statement.setString(++index, u.getLast());
			statement.setString(++index, u.getEmail());
			statement.setInt(++index, u.getUserRoleId());
			
			 statement.execute();
			//execute returns true only if it brings back data 
			 return true;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User u) {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "UPDATE users SET user_username= ?, user_password= ?,"
					+ " user_first= ?, user_last= ?, user_email= ?, user_role_id= ? "
					+ "WHERE user_id=?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setString(++index, u.getUsername());
			statement.setString(++index, u.getPassword());
			statement.setString(++index, u.getFirst());
			statement.setString(++index, u.getLast());
			statement.setString(++index, u.getEmail());
			statement.setInt(++index, u.getUserRoleId());
			statement.setInt(++index, u.getId());
			statement.execute();
			return true;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findByUserPassword(String username, String password) {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users WHERE user_username = ?"
					+ "AND user_password= ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				User u = new User(result.getInt("user_id"),
						result.getString("user_username"),
						result.getString("user_password"),
						result.getString("user_first"),
						result.getString("user_last"),
						result.getString("user_email"),
						result.getInt("user_role_id"));
				
				return u;
			}

			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users WHERE user_username = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setString(++index, username);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				User u = new User(result.getInt("user_id"),
						result.getString("user_username"),
						result.getString("user_password"),
						result.getString("user_first"),
						result.getString("user_last"),
						result.getString("user_email"),
						result.getInt("user_role_id"));
				
				return u;
			} else {
				return null;
			}

			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
