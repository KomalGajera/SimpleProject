package simpleproject.filter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.EmailValidator;

public class UserValidation implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest requestTemp = (HttpServletRequest) req; 
		String url = requestTemp.getRequestURI();		
		System.out.println(url);
				
		if(url.equals("/SimpleProject/register")) {
			boolean fname =validateName(req.getParameter("fname"));	
			boolean lname = validateName(req.getParameter("lname"));
			boolean number =validateNumber(req.getParameter("number"));
			boolean country = validateCountry(req.getParameter("country"));
			boolean state =validateState(req.getParameter("state"));
			boolean password = validatePassword(req.getParameter("password"));
			boolean dob = validateDate(req.getParameter("birthdate"));
			boolean cpassword =validateCpassword(req.getParameter("password"),req.getParameter("cpassword"));
			boolean valid = EmailValidator.getInstance().isValid(req.getParameter("email")); 
				
			if(fname == false) {
		    	req.setAttribute("fname", "please enter the proper fisrt name");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
			else if(lname == false) {
		    	req.setAttribute("lname", "please enter the proper last name ");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
			else if(!valid)
		    {
		    	req.setAttribute("email", "please enter correct email id.");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
		    else if(number == false) {
		    	req.setAttribute("number", "please enter the proper number.. ");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
		    else if(password == false) {
		    	req.setAttribute("password", "Please enter maximum 8 character of password with atleast(one capital letter,one small letter,one digit,and one special character)");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
		    else if(cpassword == false) {
		    	req.setAttribute("cpassword", "passsword does not match.");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
		    else if(dob == false) {
		    	req.setAttribute("Dob", "please enter proper date is(dd/mm/yyyy) and dont enter future date.");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
		    else if(country == false) {
		    	req.setAttribute("country", "please select country...");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
		    else if(state == false) {
		    	req.setAttribute("state", "please select state...");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
		    
		    else {
		    	chain.doFilter(req, resp);
		    }
		}		
	}
	
	public static boolean validateName(String name) 
    { 		
		if (name == null || name.contains(" ") || !Pattern.compile("^[a-zA-Z]{3,15}$").matcher(name).find()) {
			return false;
		}else {
			return true;
		}
	}
	public static boolean validateNumber(String name) 
    { 
		if (name == null || name.contains(" ") || !Pattern.compile("^((?!(0))[0-9]{10})$").matcher(name).find()) {
			return false;
		}else {
			return true;
		}
		
    }public static boolean validatePassword(String name) 
    { 
    	if (name == null || name.contains(" ") || !Pattern.compile("^([a-zA-Z0-9@*#]{8,15})$").matcher(name).find()) {
			return false;
		}else {
			return true;
		}
    }
  /* public static boolean validateDate(String name) 
    { 
    	if (name == null || name.contains(" ") || !Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$").matcher(name).find()) {
			return false;
		}else {
			return true;
		}
    }*/
    public static Boolean validateCpassword(String old,String newpassword) {
		if(old.equals(newpassword)){
			return true;
		}else {
			return false;
		}
	}
    public static boolean validateCountry(String name) 
    { 
    	System.out.println("country:"+name);
    	if (name== null || name.equals("0") ) {
			return false;
		}else {
			return true;
		}
    }
    public static boolean validateState(String name) 
    { 
    	System.out.println(name);
    	if (name == null || name.equals("0") ) {
			return false;
		}else {
			return true;
		}
    }
    public static boolean validateDate(String strDate)
    {
	 	if (strDate.trim().equals("")){
	 	    return false;
	 	}
	 	else{ 	   
	 	    SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
	 	    sdfrmt.setLenient(false); 	 
	 	    try
	 	    {
	 	    	Date todayDate = sdfrmt.parse(sdfrmt.format(new Date() ));
	 	        Date javaDate = sdfrmt.parse(strDate); 
		 	    if(javaDate.compareTo(todayDate) > 0) {
		 	       return false;
		 	    }
	 	        System.out.println(strDate+" is valid date format");
	 	    }
	 	    catch (ParseException e){
	 	        System.out.println(strDate+" is Invalid Date format");
	 	        return false;
	 	    }
	 	    return true;
	 	}
    }
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
