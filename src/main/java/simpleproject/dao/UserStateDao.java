package simpleproject.dao;

import java.util.List;

import simpleproject.entitymodel.UserState;

public interface UserStateDao {
	public int save(UserState userstate);
	public List<UserState> getAllRecords();
	public int delete(UserState usercountry);
	public List<UserState> getAllRecordsByName(UserState userstate);
	public UserState getRecordById(int id);
}
