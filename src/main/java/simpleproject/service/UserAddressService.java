package simpleproject.service;

import simpleproject.entitymodel.User;

public interface UserAddressService {
	
	public int save(User u);
	public int delete(User u);	
	public int update(User u);

}
