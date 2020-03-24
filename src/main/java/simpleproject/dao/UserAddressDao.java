package simpleproject.dao;

import java.util.List;

import simpleproject.entitymodel.User;

public interface UserAddressDao {

	public int save(User u);
	public int delete(User u);	
	public int update(User u);
	public List<User> getRecordById(int id);
	
}
