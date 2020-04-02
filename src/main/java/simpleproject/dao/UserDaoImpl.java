package simpleproject.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import simpleproject.controller.UserController;
import simpleproject.db.DatabaseConnection;
import simpleproject.entitymodel.User;
/**
* java program to perform crud operation for user information. 
* @author  komal gajera 
* @version 11.0.5 
* @since   2020-03-30 
*
*/
public class UserDaoImpl implements UserDao {
	
	DatabaseConnection d=DatabaseConnection.getInstance();
	Connection con = d.getConnection(); 
	static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
	 /** 
	    * This is the save method which use to store/update user data to database 
	    * @param user is use it contain user information.
	    * @param InputStream is use to store image.
	    * @return status contain insert success or fail information. 
	 */ 
	@SuppressWarnings("resource")
	@Override
	public int save(User u, InputStream inputStream) {
		// TODO Auto-generated method stub
		  LOGGER.info("start insertion/updation process...");
		  int status=0; 
		  int country_id = 0,state_id = 0;
		  ResultSet rs;
	      try{ 
	      PreparedStatement ps;       
	      ps=con.prepareStatement("select country_id from country_detail where country_name=?");
	      ps.setString(1, u.getCountry());
	      rs=ps.executeQuery();  
	      while(rs.next()){  
	    	    country_id=rs.getInt("country_id");	             
	      }
	      ps=con.prepareStatement("select state_id from state_detail where state_name=?");
	      ps.setString(1, u.getState());
	      rs=ps.executeQuery();
	      while(rs.next()){  
	    	    state_id=rs.getInt("state_id");	             
	      }
	      ps = con.prepareStatement("select * from user_detail where User_id=?");
          ps.setInt(1,u.getId());
          rs = ps.executeQuery();
          if(rs.next()) // means record is already available (i.e. Update record)
          {	 
        	  LOGGER.debug("start update user process...");
        	 ps=con.prepareStatement("update user_detail set fname=?,lastname=?,profile=?,password=?,gender=?,country_id=?,state_id=?,email=?,phone_no=?,hobby=?,birthdate=? where user_id=? ");
    		  ps.setString(1,u.getFname());
    		  ps.setString(2,u.getLname());
    		  ps.setBinaryStream(3,(InputStream) inputStream); 
    		  ps.setString(4,u.getPassword());
    		  ps.setString(5,u.getGender());
    		  ps.setInt(6,country_id);
    		  ps.setInt(7, state_id);
    		  ps.setString(8, u.getEmail());
    		  ps.setLong(9,u.getNumber());
    		  ps.setString(10, u.getHobby());
    		  ps.setString(11, u.getDob());
    		  ps.setInt(12, u.getId());
    		  status=ps.executeUpdate();  
          }
          else            // means no record available (i.e. insert a record)
          {	   
        	  LOGGER.debug("start insert user process...");
        	  ps=con.prepareStatement("insert into user_detail(fname,lastname,profile,password,gender,country_id,state_id,email,phone_no,hobby,birthdate) values(?,?,?,?,?,?,?,?,?,?,?)");
    		  ps.setString(1,u.getFname());
    		  ps.setString(2,u.getLname());
    		  ps.setBinaryStream(3,(InputStream) inputStream); 
    		  ps.setString(4,u.getPassword());
    		  ps.setString(5,u.getGender());
    		  ps.setInt(6,country_id);
    		  ps.setInt(7, state_id);
    		  ps.setString(8, u.getEmail());
    		  ps.setLong(9,u.getNumber());
    		  ps.setString(10, u.getHobby());
    		  ps.setString(11, u.getDob());
    		  status=ps.executeUpdate();  
          }		
	      
	     
		  }catch(Exception e){LOGGER.error("Error while insert user data"+e.getMessage());}
	       return status; 
	}
	
	
	/** 
	    * This is the getAllRecords method which use to retrieve user data from database.
	    * @param no argument.
	    * @return list this list contain information of all user. 
	    */ 
	@Override
	public List<User> getAllRecords() {
		// TODO Auto-generated method stub
		 LOGGER.info("go to retrive all records process...");
		 List<User> list=new ArrayList<User>(); 		      
		    try{  
		    	LOGGER.debug("start retriving process...");
		    	PreparedStatement ps=con.prepareStatement("select user_id,fname,lastname,profile,gender,email,phone_no,hobby,birthdate,role,country_name,state_name from user_detail u,country_detail c,state_detail s where u.country_id=c.country_id and u.state_id=s.state_id;");
		        ResultSet rs=ps.executeQuery();  
		        while(rs.next()){  		        	
		            User u=new User();  
		            u.setId(rs.getInt("user_id"));	  
		            u.setFname(rs.getString("fname"));
		            u.setLname(rs.getString("lastname"));
		            u.setImageData(rs.getBytes("profile"));
		            u.setGender(rs.getString("gender"));
		            u.setEmail(rs.getString("email"));
		            u.setNumber(rs.getLong("phone_no"));
		            u.setHobby(rs.getString("hobby"));
		            u.setDob(rs.getString("birthdate"));
		            u.setRole(rs.getString("role"));
		            u.setCountry(rs.getString("country_name"));
		            u.setState(rs.getString("state_name"));		            
		            list.add(u);  			            
		        }  
		    }catch(Exception e){LOGGER.error("Error while Retrive all user data"+e.getMessage());}
		    return list;  
	}

	
	 /** 
	    * This is the getRecordById() which use to get record by id.
	    * @param id is use it contain id of particular user.
	    * @return User contain information of user which id is match. 
	 */ 
	@Override
	public User getRecordById(int id) {
		// TODO Auto-generated method stub
		LOGGER.info("do retrive data by id...");
		User u=null;		      
	    try{  
	    	 LOGGER.debug("start retrive data by id...");
	    	PreparedStatement ps=con.prepareStatement("select user_id,fname,lastname,password,profile,gender,email,phone_no,hobby,birthdate,role,country_name,state_name from user_detail u,country_detail c,state_detail s where u.country_id=c.country_id and u.state_id=s.state_id and user_id=?");
	    	ps.setInt(1, id);
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  		        	
	            u=new User();  
	            u.setId(rs.getInt("user_id"));	  
	            u.setFname(rs.getString("fname"));
	            u.setLname(rs.getString("lastname"));
	            u.setImageData(rs.getBytes("profile"));
	            u.setGender(rs.getString("gender"));
	            u.setEmail(rs.getString("email"));
	            u.setPassword(rs.getString("password"));
	            u.setNumber(rs.getLong("phone_no"));
	            u.setHobby(rs.getString("hobby"));
	            u.setDob(rs.getString("birthdate"));
	            u.setRole(rs.getString("role"));
	            u.setCountry(rs.getString("country_name"));
	            u.setState(rs.getString("state_name"));	
	         }  
	    }catch(Exception e){LOGGER.error("Error while retrive particular user data"+e.getMessage());}
	    return u;  
	}

	 /** 
	    * This is the checkuser() which use to check user is admin or normal user.
	    * @param user is use to contain information of user.
	    * @return status contain information about user is exits or not. 
	 */ 
	@Override
	public int checkuser(User u) {
		// TODO Auto-generated method stub
		 LOGGER.info("do check user is exits...");
		int status=0;
		  try{ 
			  LOGGER.debug("start to check user is exits...");
		      PreparedStatement ps=con.prepareStatement("select * from user_detail where email=? and password=?");
			  ps.setString(1,u.getEmail());
			  ps.setString(2,u.getPassword());
			  ResultSet rs=ps.executeQuery();
			  if (rs.next()) {  
		          u.setFname(rs.getString("fname"));
		          u.setId(rs.getInt("user_id"));
		          u.setRole(rs.getString("role"));	
		          status=1;
			  } 
			  }catch(Exception e){LOGGER.error("Error while checkuser "+e.getMessage());} 
		  	  return status;
	}
	
	/** 
	    * This is the getImageTable() which use to get user profile.
	    * @param name is use to get image by name.
	    * @return User contain profile picture. 
	*/ 
	public User getImageInTable(String name) {
		// TODO Auto-generated method stub
		
	      User u=new User();
		try {
			 LOGGER.debug("start retrive user image...");
			PreparedStatement ps = con.prepareStatement("select fname,profile from user_detail where fname = ?");
			  ps.setString(1, name);
		      ResultSet rs = ps.executeQuery();
		      if (rs.next()) {
		       
		          byte[] imageData = rs.getBytes("profile");
		          String imageFileName = rs.getString("fname");
		          u.setImageFileName(imageFileName);
		          u.setImageData(imageData);
		          return u;
		      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error while retrive user image"+e.getMessage());
		}
	    
	      return null;
	}
	
	/** 
	    * This is the usercheck() which use to check user is exits at registration time.
	    * @param email is use to check user is exits.
	    * @return status contain information about user is exits or not. 
	*/ 
	@Override
	public int usercheck(String email) {
		// TODO Auto-generated method stub
		int status=0;
		  try{ 
			  LOGGER.debug("start to check user at registration time process...");
		      PreparedStatement ps=con.prepareStatement("select * from user_detail where email=?");
			  ps.setString(1,email);
			  ResultSet rs=ps.executeQuery();
			  if (rs.next()) {  
		          status=1;
			  } 
			  }catch(Exception e){LOGGER.error("Error while check user is exits or not:"+e.getMessage());} 
		  	  return status;
	}
	
	/** 
	    * This is the delete() which use to delete.
	    * @param id is use which contain id of user for delete.
	    * @return status contain information about user is exits or not. 
	 */ 
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		int status=0;
		  try{ 		
			  LOGGER.debug("start delete process for user...");
		      PreparedStatement ps=con.prepareStatement("delete from user_detail where user_id=?");
			  ps.setInt(1,id);
			  status=ps.executeUpdate();
			  }catch(Exception e){LOGGER.error("Error while delete user data"+e.getMessage());} 
		  	  return status;
	}
	
	
	/** 
	    * This is the getUserByEmail() which use to change user password.
	    * @param user is use to contain information of user.
	    * @return status contain information about password is change or not. 
	 */ 
	@Override
	public int getUserByEmail(User user) {
		// TODO Auto-generated method stub
		LOGGER.info("do update new password...");
		int status=0;
		PreparedStatement ps;
		ResultSet rs;
		 try{ 
			  LOGGER.debug("start update new password...");
		      ps=con.prepareStatement("select * from user_detail where email=?");
			  ps.setString(1,user.getEmail());
			  rs=ps.executeQuery();
			  if (rs.next()) {  
				  ps=con.prepareStatement("update user_detail set password=? where email=?");
	    		  ps.setString(1,user.getPassword());	
	    		  ps.setString(2, user.getEmail());
	    		  status=ps.executeUpdate(); 
			  } 
			  }catch(Exception e){LOGGER.error("Error while change user password"+e.getMessage());} 
		return status;
	}

	
}
