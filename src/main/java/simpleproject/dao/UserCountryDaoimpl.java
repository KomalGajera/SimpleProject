package simpleproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import simpleproject.db.DatabaseConnection;
import simpleproject.entitymodel.UserCountry;

public class UserCountryDaoimpl implements UserCountryDao {
	
	DatabaseConnection d=DatabaseConnection.getInstance();
	Connection con = d.getConnection(); 	
	

	@Override
	public int save(UserCountry usercountry) {
		// TODO Auto-generated method stub
		  int status=0; 
	      try{  PreparedStatement
		  ps=con.prepareStatement("insert into country_detail(country_name) values(?)");
		  ps.setString(1,usercountry.getCountry_name());		 
		  status=ps.executeUpdate(); 
		  }catch(Exception e){System.out.println(e);} 
	       return status; 
	}

	@Override
	public List<UserCountry> getAllRecords() {
		// TODO Auto-generated method stub
		 List<UserCountry> list=new ArrayList<UserCountry>(); 		      
		    try{  
		        
		    	PreparedStatement ps=con.prepareStatement("select * from country_detail");
		        ResultSet rs=ps.executeQuery();  
		        while(rs.next()){  		        	
		            UserCountry u=new UserCountry();  
		            u.setCountry_id(rs.getInt("country_id"));
		            u.setCountry_name(rs.getString("country_name"));		            
		            list.add(u);  
		        }  
		    }catch(Exception e){System.out.println(e);}
		    return list;  
	}
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
