package simpleproject.dao;

import java.util.List;

import simpleproject.entitymodel.UserCountry;

public interface UserCountryDao {

	
	public int save(UserCountry usercountry);
	public List<UserCountry> getAllRecords();
	public int delete(UserCountry usercountry);
}
