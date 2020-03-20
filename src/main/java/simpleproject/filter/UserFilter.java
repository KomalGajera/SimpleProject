package simpleproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class UserFilter implements Filter {

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
	
		
		if(url.equals("/SimpleProject/register")) {			
		    	chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/useraddress")) {			
	    	chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/checkemail")) {			
	    	chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/countryadd")) {
			chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/displaystate")) {
			chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/stateupdate")) {
			chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/countryupdate")) {
			chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/statedelete")) {
			chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/countrydelete")) {
			chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/stateadd")) {
			
	    	chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/login")) {
			
	    	chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/displayuser")) {
			
	    	chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/userbyid")) {
			
	    	chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/userdelete")) {
			
	    	chain.doFilter(req, resp);
		}
		if(url.equals("/SimpleProject/displaycountry")) {
			
	    	chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
