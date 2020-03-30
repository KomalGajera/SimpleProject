package simpleproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import simpleproject.db.DatabaseConnection;
import simpleproject.entitymodel.User;

public class UserAddressDaoImpl implements UserAddressDao {
	
	DatabaseConnection d=DatabaseConnection.getInstance();
	Connection con = d.getConnection(); 
	

	@SuppressWarnings("resource")
	@Override
	public int save(User u) {
		// TODO Auto-generated method stub
		System.out.println("first");
		int status=0; 
		int user_id=0;
		String address[]=u.getAddress();
		List<String> oldaddress=new ArrayList<String>(); 
		List<String> newaddress=new ArrayList<String>();
		ResultSet rs,rs1;
		 try{ 		
		      PreparedStatement ps;
		      ps = con.prepareStatement("select * from user_address where user_id=?");
	          ps.setInt(1,u.getId());
	          rs = ps.executeQuery();
	          if(rs.next()) // means record is already available (i.e. Update record)
	          {	 
	        	  oldaddress.add(rs.getString("address"));
	        	  while(rs.next()) {
	        	  oldaddress.add(rs.getString("address"));
	        	  }
	        	  for (int i = 0; i < address.length;  i++) {
						newaddress.add(address[i]);				
				  }
	        	  List<String> oldaddress1=new ArrayList<String>(oldaddress); 
	        	  oldaddress.removeAll(newaddress); 
	        	  newaddress.removeAll(oldaddress1);
	        	  for (String old : oldaddress) { 
	        		  ps=con.prepareStatement("Delete from user_address where user_id=? and address=?");
				      ps.setInt(1, u.getId());
				      ps.setString(2, old);
				      status=ps.executeUpdate(); 			      
	        	  }
		          for (String newa : newaddress) {
		        	  ps=con.prepareStatement("insert into user_address(user_id,address) values(?,?)");
					  ps.setInt(1, u.getId());
					  ps.setString(2,newa);
					  status=ps.executeUpdate(); 
		          }	 
		          status=1;
	          }
	          else            // means no record available (i.e. insert a record)
	          {	        	 
	        	  ps=con.prepareStatement("select user_id from user_detail where email=?");
			      ps.setString(1, u.getEmail());
			      rs=ps.executeQuery();  
			      while(rs.next()){  
			    	    user_id=rs.getInt("user_id");
			      }
			      for (int i = 0; i < address.length;  i++) {
					 ps=con.prepareStatement("insert into user_address(user_id,address) values(?,?)");
					  ps.setInt(1,user_id);
					  ps.setString(2,address[i]);
					  status=ps.executeUpdate(); 				
			      }
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
	public List<User> getRecordById(int id) {
		// TODO Auto-generated method stub
		List<User> list=new ArrayList<User>(); 
		
		 try{  
		       
		        PreparedStatement ps=con.prepareStatement("select * from user_address where user_id=?");  
		        ps.setInt(1,id);  
		        ResultSet rs=ps.executeQuery();  
		        while(rs.next()){   
		           User u=new User();
		           u.setAdd(rs.getString("address"));
		           list.add(u);		          
		        }  
		    }catch(Exception e){System.out.println(e);}  
		return list;
	}

}
