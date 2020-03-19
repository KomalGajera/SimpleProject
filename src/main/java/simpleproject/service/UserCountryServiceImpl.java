package simpleproject.service;

import java.util.List;

import simpleproject.dao.UserCountryDao;
import simpleproject.dao.UserCountryDaoimpl;
import simpleproject.entitymodel.UserCountry;

public class UserCountryServiceImpl implements UserCountryService {

	static UserCountryDao countrydao=new UserCountryDaoimpl();
	
	@Override
	public List<UserCountry> getAllRecords() {
		List<UserCountry> list=countrydao.getAllRecords();
		return list;
		
	}

	@Override
	public int save(UserCountry usercountry) {
		// TODO Auto-generated method stub
		int status=countrydao.save(usercountry);
		return status;
	}
	
	

}
