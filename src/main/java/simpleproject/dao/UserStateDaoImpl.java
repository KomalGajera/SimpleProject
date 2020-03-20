package simpleproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import simpleproject.db.DatabaseConnection;
import simpleproject.entitymodel.UserState;

public class UserStateDaoImpl implements UserStateDao {

	
	DatabaseConnection d=DatabaseConnection.getInstance();
	Connection con = d.getConnection();
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
		
		  }catch(Exception e){System.out.println(e);} 
	       return status; 
	}

	@Override
	public List<UserState> getAllRecords() {
		// TODO Auto-generated method stub
		 List<UserState> list=new ArrayList<UserState>(); 
		 int country_id=0;
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
		    }catch(Exception e){System.out.println(e);}
		    return list;
	}

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
		    }catch(Exception e){System.out.println(e);}
		    return list;
	}

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
	    }catch(Exception e){System.out.println(e);}  
	    return u; 
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		int status=0;  
	    try{ 		         
	        PreparedStatement ps=con.prepareStatement("delete from state_detail where state_id=?");  
	        ps.setInt(1,id);  
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	  
	    return status;  
	}
		 

}
