package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

public class UserRoleDAO implements IUserRoleDAO {

	@Override
	public UserRole findById(int id) {
		try(Connection conn= ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users u JOIN user_roles ur ON  u.user_role_id = ur.user_role_id WHERE u.user_id = ?;";			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index=0;
			statement.setInt(++index, id);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				UserRole ur = new UserRole(result.getInt("user_role_id"),
						result.getString("user_role"));
				
				return ur;
			} else {
				return null;
			}

			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
