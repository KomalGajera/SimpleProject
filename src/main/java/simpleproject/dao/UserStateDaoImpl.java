package simpleproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import simpleproject.db.DatabaseConnection;
import simpleproject.entitymodel.UserState;
/**
* java program to perform crud operation for user-state information. 
* @author  komal gajera 
* @version 11.0.5 
* @since   2020-03-30  
*
*/
public class UserStateDaoImpl implements UserStateDao {

	
	DatabaseConnection d=DatabaseConnection.getInstance();
	Connection con = d.getConnection();	
	static final Logger LOGGER = Logger.getLogger(UserStateDaoImpl.class);
	
	/** 
	    * This is the save method which use to store user-state data to database 
	    * @param userstate is use it contain state information.
	    * @return status contain insert success or fail information. 
	    */
	@SuppressWarnings("resource")     
	@Override
	public int save(UserState userstate) {
		// TODO Auto-generated method stub
		 int status=0; 
		  int country_id = 0;
		  ResultSet rs;
	      try{ 
	      PreparedStatement ps;       
	      ps=con.prepareStatement("select country_id from country_detail where country_name=?");
	      ps.setString(1, userstate.getCountry_name());
	      rs=ps.executeQuery();  
	      while(rs.next()){  
	    	    country_id=rs.getInt("country_id");	             
	      }	  
	      ps = con.prepareStatement("select * from state_detail where state_id=?");
          ps.setInt(1,userstate.getState_id());
          rs = ps.executeQuery();
          if(rs.next()) // means record is already available (i.e. Update record)
          {	        	 
        	  ps=con.prepareStatement("update state_detail set state_name=?,country_id=? where state_id=?");
    		  ps.setString(1,userstate.getState_name());	
    		  ps.setInt(2,country_id);
    		  ps.setInt(3, userstate.getState_id());
    		  status=ps.executeUpdate(); 
          }
          else            // means no record available (i.e. insert a record)
          {	        	 
        	  ps=con.prepareStatement("insert into state_detail(state_name,country_id) values(?,?)");
    		  ps.setString(1,userstate.getState_name());
    		  ps.setInt(2,country_id);	
    		  status=ps.executeUpdate(); 
          }		
		
		  }catch(Exception e){LOGGER.error("Error while insert user-state data"+e.getMessage());} 
	       return status; 
	}

	
	
	 /** 
	    * This is the getAllRecords method which use to retrieve user-state data from database.
	    * @param no argument.
	    * @return list<UserState> this list contain information of all state. 
	    */ 
	@Override
	public List<UserState> getAllRecords() {
		// TODO Auto-generated method stub
		 List<UserState> list=new ArrayList<UserState>(); 
		 PreparedStatement ps;
		 ResultSet rs;
		    try{ 
		        	ps=con.prepareStatement("select state_id,state_name,country_name from state_detail s,country_detail c where s.country_id=c.country_id");
		        	rs=ps.executeQuery();  
			        while(rs.next()){ 
			        	UserState u1=new UserState();
			            u1.setState_id(rs.getInt("state_id"));
			            u1.setState_name(rs.getString("state_name"));	
			            u1.setCountry_name(rs.getString("country_name"));
			            list.add(u1);  
			        } 	    	
		    }catch(Exception e){LOGGER.error("Error while retrive user-state data"+e.getMessage());}
		    return list;
	}
	
	
	 /** 
	    * This is the getAllMethodByName() which use to get record for particular user-state from database 
	    * @param userstate is use it contain state information.
	    * @return list contain information of particular state. 
    */ 
	@Override
	public List<UserState> getAllRecordsByName(UserState userstate) {
		// TODO Auto-generated method stub
		
		 List<UserState> list=new ArrayList<UserState>(); 
		 PreparedStatement ps;
		 ResultSet rs;
		    try{ 		    	   
		    	 ps=con.prepareStatement("select state_name from state_detail where country_id=(select country_id from country_detail where country_name=?)");
		    	 ps.setString(1, userstate.getCountry_name());
		    	 rs=ps.executeQuery();  
			        while(rs.next()){ 	
			        	UserState u=new UserState();
			            u.setState_name(rs.getString("state_name"));
			            list.add(u);  
			        }	    	
		    }catch(Exception e){LOGGER.error("Error while retrive user-state data by name"+e.getMessage());}
		    return list;
	}
	
	
	 /** 
	    * This is the getRecordById() which use to get record by id.
	    * @param id is use it contain id of particular state.
	    * @return UserState contain information of state which id is match. 
	 */ 
	@Override
	public UserState getRecordById(int id) {
		// TODO Auto-generated method stub
		UserState u=null;  
	    try{  
	       
	        PreparedStatement ps=con.prepareStatement("select state_id,country_name,state_name from state_detail s,country_detail c where c.country_id=s.country_id and state_id=?");  
	        ps.setInt(1,id);  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            u=new UserState();  
	            u.setState_id((rs.getInt("state_id"))); 
	            u.setCountry_name(rs.getString("country_name"));  
	            u.setState_name(rs.getString("State_name"));
	           
	        }  
	    }catch(Exception e){LOGGER.error("Error while retrive user-state data by id"+e.getMessage());}  
	    return u; 
	}
	
	
	 /** 
	    * This is the delete() which use to delete record by id.
	    * @param id is use it contain id of particular state.
	    * @return status contain information of state is delete success or fail. 
	 */ 
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		int status=0;  
	    try{ 		         
	        PreparedStatement ps=con.prepareStatement("delete from state_detail where state_id=?");  
	        ps.setInt(1,id);  
	        status=ps.executeUpdate();  
	    }catch(Exception e){LOGGER.error("Error while retrive delete state data"+e.getMessage());}  
	  
	    return status;  
	}
		 

}
