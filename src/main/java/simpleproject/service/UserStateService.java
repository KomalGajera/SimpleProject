package simpleproject.service;

import java.util.List;

import simpleproject.entitymodel.UserState;


public interface UserStateService {
	public List<UserState> getAllRecords();

	public int save(UserState userstate);

	public List<UserState> getAllRecordsByName(UserState userstate);

	public UserState getRecordById(int id);
}
