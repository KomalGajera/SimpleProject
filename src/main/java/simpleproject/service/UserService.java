package simpleproject.service;

import java.io.InputStream;
import java.util.List;

import simpleproject.entitymodel.User;


public interface UserService {
	
	public int update(User u);
	public List<User> getAllRecords();
	/* public List<User> getAllRecordsByCategory(UserSearch usersearch); */
	public int save(User user, InputStream inputStream);
	public int checkuser(User user);
	public int usercheck(String email);
	public int delete(int id);
	public User getRecordById(int id);

}
