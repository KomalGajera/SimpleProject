package simpleproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import simpleproject.db.DatabaseConnection;
import simpleproject.entitymodel.User;

public class UserAddressDaoImpl implements UserAddressDao {
	
	DatabaseConnection d=DatabaseConnection.getInstance();
	Connection con = d.getConnection(); 
	

	@Override
	public int save(User u) {
		// TODO Auto-generated method stub
		int status=0; 
		int user_id=0;
		String address[]=u.getAddress();
		ResultSet rs;
		 try{ 
		      PreparedStatement ps;  			     
		      ps=con.prepareStatement("select user_id from user_detail where fname=?");
		      ps.setString(1, u.getFname());
		      rs=ps.executeQuery();  
		      while(rs.next()){  
		    	    user_id=rs.getInt("user_id");
		      }
			for (int i = 0; address[i] != null; i++) {
				 ps=con.prepareStatement("insert into user_address(user_id,address) values(?,?)");
				  ps.setInt(1,user_id);
				  ps.setString(2,address[i]);
				  status=ps.executeUpdate(); 	             
					
			}
	}catch(Exception e){System.out.println(e);}
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

	@Override
	public List<String> getRecordById(int id) {
		// TODO Auto-generated method stub
		List<String> list = null;
		return list;
	}

}
