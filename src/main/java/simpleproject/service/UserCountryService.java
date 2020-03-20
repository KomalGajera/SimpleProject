package simpleproject.service;

import java.util.List;

import simpleproject.entitymodel.UserCountry;
import simpleproject.entitymodel.UserState;

public interface UserCountryService {
	public List<UserCountry> getAllRecords();

	public int save(UserCountry usercountry);

	public int delete(int id);

	public UserCountry getRecordById(int id);

	
}
