package simpleproject.dao;

import java.io.InputStream;
import java.util.List;

import simpleproject.entitymodel.User;

public interface UserDao {

	public int save(User u, InputStream inputStream);
	public List<User> getAllRecords();
	public int delete(int id);
	public User getRecordById(int id);
	public int update(User u);
	public int checkuser(User u);
	public User getImageInTable(String name);
	public int usercheck(String email); 

}
