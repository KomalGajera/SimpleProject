package simpleproject.service;

import simpleproject.dao.UserAddressDao;
import simpleproject.dao.UserAddressDaoImpl;
import simpleproject.entitymodel.User;

public class UserAddressServiceImpl implements UserAddressService {

	UserAddressDao useraddress=new UserAddressDaoImpl();
	@Override
	public int save(User u) {
		// TODO Auto-generated method stub
		int status=useraddress.save(u);
		return status;
	}

	@Override
	public int delete(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

}
