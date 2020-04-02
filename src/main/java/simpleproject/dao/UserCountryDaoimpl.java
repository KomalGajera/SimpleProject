package simpleproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import simpleproject.db.DatabaseConnection;
import simpleproject.entitymodel.UserCountry;
/**
* java program to perform crud operation for user-country information. 
* @author  komal gajera 
* @version 11.0.5 
* @since   2020-03-30  
*
*/
public class UserCountryDaoimpl implements UserCountryDao {
	
	DatabaseConnection d=DatabaseConnection.getInstance();
	Connection con = d.getConnection(); 	
	static final Logger LOGGER = Logger.getLogger(UserCountryDaoimpl.class);

	
	/** 
	    * This is the save method which use to store user-country data to database 
	    * @param usercountry is use it contain country information.
	    * @return status contain insert success or fail information. 
	*/
	@SuppressWarnings("resource")	
	@Override
	public int save(UserCountry usercountry) {
		// TODO Auto-generated method stub
		  int status=0;
		  PreparedStatement ps;
	      try{  
	    	  ps = con.prepareStatement("select * from country_detail where country_id=?");
	          ps.setInt(1,usercountry.getCountry_id());
	          ResultSet rs = ps.executeQuery();
	          if(rs.next()) // means record is already available (i.e. Update record)
	          {	        	 
	        	  ps=con.prepareStatement("update country_detail set country_name=? where country_id=?");
	    		  ps.setString(1,usercountry.getCountry_name());	
	    		  ps.setInt(2, usercountry.getCountry_id());
	    		  status=ps.executeUpdate(); 
	          }
	          else            // means no record available (i.e. insert a record)
	          {	        	 
	        	  ps=con.prepareStatement("insert into country_detail(country_name) values(?)");
	    		  ps.setString(1,usercountry.getCountry_name());		 
	    		  status=ps.executeUpdate(); 
	          }		 
		  }catch(Exception e){LOGGER.error("Error while insert user-country data"+e.getMessage());} 
	       return status; 
	}
	
	
	 /** 
	    * This is the getAllRecords method which use to retrieve user-country data from database.
	    * @param no argument.
	    * @return list this list contain information of all country. 
	    */
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
		    }catch(Exception e){LOGGER.error("Error while retrive user-country data"+e.getMessage());}
		    return list;  
	}
	
	
	 /** 
	    * This is the delete() which use to delete record by id.
	    * @param id is use it contain id of particular country.
	    * @return status contain information of country is delete success or fail. 
	 */ 
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		int status=0;  
		PreparedStatement ps;
	    try{ 		         
	    	ps=con.prepareStatement("delete from state_detail where country_id=?");  
	        ps.setInt(1,id);  
	        ps.executeUpdate();  
	        	ps=con.prepareStatement("delete from country_detail where country_id=?");  
		        ps.setInt(1,id);  
		        status=ps.executeUpdate();	        
	    }catch(Exception e){LOGGER.error("Error while delete user-country data"+e.getMessage());}  
	  
	    return status; 
	}
	
	
	 /** 
	    * This is the getRecordById() which use to get record by id.
	    * @param id is use it contain id of particular country.
	    * @return UserCountry contain information of country which id is match. 
	 */
	@Override
	public UserCountry getRecordById(int id) {
		// TODO Auto-generated method stub
		UserCountry u=null;  
	    try{  
	       
	        PreparedStatement ps=con.prepareStatement("select * from country_detail where country_id=?");  
	        ps.setInt(1,id);  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            u=new UserCountry();  
	            u.setCountry_id((rs.getInt("country_id"))); 
	            u.setCountry_name(rs.getString("country_name"));  
	           
	        }  
	    }catch(Exception e){LOGGER.error("Error while retrive user-country data by id "+e.getMessage());}  
	    return u; 
	}
	
	

}
