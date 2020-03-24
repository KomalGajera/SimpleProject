package simpleproject.service;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import simpleproject.dao.UserDao;
import simpleproject.dao.UserDaoImpl;
import simpleproject.entitymodel.User;

public class UserServiceImpl implements UserService {

	UserDao userdao=new UserDaoImpl();
	User user=new User();

	@Override
	public int update(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getAllRecords() {
		// TODO Auto-generated method stub
		List<User> userlist=userdao.getAllRecords();
		return userlist;
	}

	@Override
	public int save(User user, InputStream inputStream) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 try {
	            Date date = formatter.parse(user.getDob());
	            formatter.applyPattern("yyyy-MM-dd");
	            user.setDob(formatter.format(date));

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		int status=userdao.save(user,inputStream);
		return status;
	}

	@Override
	public int checkuser(User user) {
		// TODO Auto-generated method stub
		int status=userdao.checkuser(user);
		return status;
	}

	@Override
	public int usercheck(String email) {
		// TODO Auto-generated method stub
		int status=userdao.usercheck(email);
		return status;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		int status=userdao.delete(id);
		return status;
	}

	@Override
	public User getRecordById(int id) {
		// TODO Auto-generated method stub
		User u=userdao.getRecordById(id);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 try {
	            Date date = formatter.parse(u.getDob());
	            formatter.applyPattern("dd/MM/yyyy");
	            u.setDob(formatter.format(date));

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		
		return u;
	}

	@Override
	public int getUserByEmail(User user) {
		// TODO Auto-generated method stub
		int status=userdao.getUserByEmail(user);
		return status;
	}

}
