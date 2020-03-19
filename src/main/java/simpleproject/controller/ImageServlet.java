package simpleproject.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simpleproject.dao.UserDao;
import simpleproject.dao.UserDaoImpl;
import simpleproject.entitymodel.User;


public class ImageServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("image/jpg"); 
		UserDao user=new UserDaoImpl(); 
        ServletOutputStream out = resp.getOutputStream();  
        try {  
            /* 
             * TODO output your page here. You may use following sample code. 
             */ 		
			  String name = req.getParameter("name");
			  
			  User u=user.getImageInTable(name);
			  System.out.println("name is::"+u.getImageFileName());
			  resp.getOutputStream().write(u.getImageData()); out.write(u.getImageData());
			 
            

           
        } catch (Exception ex) {  
            out.println(ex.getMessage());  
        } 
    }   
	
}
