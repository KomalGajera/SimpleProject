package simpleproject.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import simpleproject.db.DatabaseConnection;
import simpleproject.entitymodel.User;
import simpleproject.entitymodel.UserCountry;

public class UserDaoImpl implements UserDao {
	
	DatabaseConnection d=DatabaseConnection.getInstance();
	Connection con = d.getConnection(); 

	@SuppressWarnings("resource")
	@Override
	public int save(User u, InputStream inputStream) {
		// TODO Auto-generated method stub
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
    		  ps.setInt(11, u.getId());
    		  status=ps.executeUpdate();  
          }
          else            // means no record available (i.e. insert a record)
          {	        	 
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
	      
	     
		  }catch(Exception e){System.out.println(e);}
	       return status; 
	}
	@Override
	public List<User> getAllRecords() {
		// TODO Auto-generated method stub
		 List<User> list=new ArrayList<User>(); 		      
		    try{  
		        
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
		    }catch(Exception e){System.out.println(e);}
		    return list;  
	}


	@Override
	public User getRecordById(int id) {
		// TODO Auto-generated method stub
		User u=null;		      
	    try{  
	        
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
	            System.out.print(rs.getLong("phone_no"));
	         }  
	    }catch(Exception e){System.out.println(e);}
	    return u;  
	}

	@Override
	public int update(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkuser(User u) {
		// TODO Auto-generated method stub
		int status=0;
		  try{ 
			  
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
			  }catch(Exception e){System.out.println(e);} 
		  	  return status;
	}
	
	public User getImageInTable(String name) {
		// TODO Auto-generated method stub
	      User u=new User();
		try {
			PreparedStatement ps = con.prepareStatement("select fname,profile from user_detail where fname = ?");
			  ps.setString(1, name);
		      ResultSet rs = ps.executeQuery();
		      if (rs.next()) {
		         System.out.println("record find..");
		          byte[] imageData = rs.getBytes("profile");
		          String imageFileName = rs.getString("fname");
		          u.setImageFileName(imageFileName);
		          u.setImageData(imageData);
		          return u;
		      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	      return null;
	}
	@Override
	public int usercheck(String email) {
		// TODO Auto-generated method stub
		int status=0;
		  try{ 
			  
		      PreparedStatement ps=con.prepareStatement("select * from user_detail where email=?");
			  ps.setString(1,email);
			  ResultSet rs=ps.executeQuery();
			  if (rs.next()) {  
		          status=1;
			  } 
			  }catch(Exception e){System.out.println(e);} 
		  	  return status;
	}
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		int status=0;
		  try{ 			  
		      PreparedStatement ps=con.prepareStatement("delete from user_detail where user_id=?");
			  ps.setInt(1,id);
			  status=ps.executeUpdate();
			  }catch(Exception e){System.out.println(e);} 
		  	  return status;
	}
	@Override
	public int getUserByEmail(User user) {
		// TODO Auto-generated method stub
		int status=0;
		PreparedStatement ps;
		ResultSet rs;
		 try{ 
			  
		      ps=con.prepareStatement("select * from user_detail where email=?");
			  ps.setString(1,user.getEmail());
			  rs=ps.executeQuery();
			  if (rs.next()) {  
				  ps=con.prepareStatement("update user_detail set password=? where email=?");
	    		  ps.setString(1,user.getPassword());	
	    		  ps.setString(2, user.getEmail());
	    		  status=ps.executeUpdate(); 
			  } 
			  }catch(Exception e){System.out.println(e);} 
		return status;
	}

	
}
