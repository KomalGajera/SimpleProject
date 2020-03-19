package simpleproject.service;

import java.util.List;

import simpleproject.dao.UserStateDao;
import simpleproject.dao.UserStateDaoImpl;
import simpleproject.entitymodel.UserState;

public class UserStateServiceImpl implements UserStateService {
	
	
	UserStateDao statedao=new UserStateDaoImpl();

	@Override
	public List<UserState> getAllRecords() {
		// TODO Auto-generated method stub
		List<UserState> list=statedao.getAllRecords();
		return list;
	}

	@Override
	public int save(UserState userstate) {
		// TODO Auto-generated method stub
		int status=statedao.save(userstate);
		return status;
	}

	@Override
	public List<UserState> getAllRecordsByName(UserState userstate) {
		// TODO Auto-generated method stub
		List<UserState> list=statedao.getAllRecordsByName(userstate);
		return list;
	}

	@Override
	public UserState getRecordById(int id) {
		// TODO Auto-generated method stub
		UserState u=statedao.getRecordById(id);
		return u;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		int status=statedao.delete(id);
		return status;
	}

}
