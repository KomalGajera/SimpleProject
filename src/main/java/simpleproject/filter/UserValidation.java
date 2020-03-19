package simpleproject.filter;

import java.io.IOException;
import java.util.regex.Matcher;
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
				
		if(url.equals("/SimpleProject/login")) {
			String fname = req.getParameter("fname");			
			String lname = req.getParameter("lname");
			String email = req.getParameter("email");
			
			boolean valid = EmailValidator.getInstance().isValid(email); 
			if(fname.equals("") || !validateName(fname)) {
		    	req.setAttribute("message", "please enter the proper fisrt name");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
		    else if(lname.equals("") || !validateName(lname)) {
		    	req.setAttribute("message", "please enter the proper last name ");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
		    else if(!valid)
		    {
		    	req.setAttribute("message", "please enter correct email id.");
		    	req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		    }
		    else {
		    	chain.doFilter(req, resp);
		    }
		}
		
		
}

	public static boolean validateName(String txt) 
    { 
        String regex = "^[a-zA-Z ]+$"; 
        Pattern pattern = Pattern.compile(regex, 
                                          Pattern.CASE_INSENSITIVE); 
        Matcher matcher = pattern.matcher(txt); 
        return matcher.find(); 
    }
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
