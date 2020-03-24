package simpleproject.service;

import java.util.List;

import simpleproject.entitymodel.User;

public interface UserAddressService {
	
	public int save(User u);
	public int delete(User u);	
	public int update(User u);
	public List<User> getRecordById(int id);

}
