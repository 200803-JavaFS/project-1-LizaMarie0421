package com.revature.daos;

import com.revature.models.ReimbStatus;

public interface IReimbStatusDAO {

	public ReimbStatus selectByStatus(String status);
}
