package com.revature.daos;

import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;

public interface IReimbTypeDAO {

	public ReimbType selectByType(String type);
}
