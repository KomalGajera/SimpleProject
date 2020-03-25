package simpleproject.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import simpleproject.entitymodel.User;
import simpleproject.entitymodel.UserCountry;
import simpleproject.entitymodel.UserState;
import simpleproject.service.UserAddressService;
import simpleproject.service.UserAddressServiceImpl;
import simpleproject.service.UserCountryService;
import simpleproject.service.UserCountryServiceImpl;
import simpleproject.service.UserService;
import simpleproject.service.UserServiceImpl;
import simpleproject.service.UserStateService;
import simpleproject.service.UserStateServiceImpl;

@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
		maxFileSize = 10485760L, // 10 MB
		maxRequestSize = 20971520L // 20 MB
)
public class UserController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "uploads";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		UserCountry usercountry=new UserCountry();
		UserState userstate=new UserState();
		UserCountryService userCountrys = new UserCountryServiceImpl();
		UserStateService userstates = new UserStateServiceImpl();
		UserService userservice = new UserServiceImpl();
		UserAddressService useraddress=new UserAddressServiceImpl();
		HttpServletRequest requestTemp = (HttpServletRequest) req;
		HttpSession session = req.getSession();
		String url = requestTemp.getRequestURI();
		String json = null;		
		if (url.equals("/SimpleProject/displaycountry")) {
			List<UserCountry> list = userCountrys.getAllRecords();
			json = new Gson().toJson(list);
			resp.setContentType("application/json");
            resp.getWriter().write(json);			
		}
		if(url.equals("/SimpleProject/userbyid")) {
			int id=Integer.parseInt(req.getParameter("id"));
			user=userservice.getRecordById(id);
			json = new Gson().toJson(user);
			resp.setContentType("application/json");
            resp.getWriter().write(json);
			
		}
		if(url.equals("/SimpleProject/useraddress")) {
			int id=Integer.parseInt(req.getParameter("id"));
			System.out.println(id);
			List<User> list=useraddress.getRecordById(id);
			json = new Gson().toJson(list);
			resp.setContentType("application/json");
            resp.getWriter().write(json);
			
		}
		if(url.equals("/SimpleProject/stateupdate")) {
			int id=Integer.parseInt(req.getParameter("id"));
			userstate=userstates.getRecordById(id);
			json = new Gson().toJson(userstate);
			resp.setContentType("application/json");
            resp.getWriter().write(json);			
		}
		if(url.equals("/SimpleProject/countryupdate")) {
			int id=Integer.parseInt(req.getParameter("id"));
			usercountry=userCountrys.getRecordById(id);
			json = new Gson().toJson(usercountry);
			resp.setContentType("application/json");
            resp.getWriter().write(json);
		}
		if(url.equals("/SimpleProject/statedelete")) {
			int id=Integer.parseInt(req.getParameter("id"));
			int status=userstates.delete(id);
			if(status==1) {
				req.getRequestDispatcher("/addstate.jsp").forward(req, resp);
			}else {
				req.setAttribute("errormessage","There is some error in insert country...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp); 
			}	
		}
		if(url.equals("/SimpleProject/userdelete")) {
			int id=Integer.parseInt(req.getParameter("id"));
			int status=userservice.delete(id);
			if(status==1) {
				req.getRequestDispatcher("/user.jsp").forward(req, resp);
			}else {
				req.setAttribute("errormessage","There is some error in insert country...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp); 
			}	
		}
		if(url.equals("/SimpleProject/countrydelete")) {
			int id=Integer.parseInt(req.getParameter("id"));
			int status=userCountrys.delete(id);	
			if(status==1) {
				req.getRequestDispatcher("/addcountry.jsp").forward(req, resp);
			}else {
				req.setAttribute("errormessage","There is some error in insert country...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp); 
			}	
		}
		if (url.equals("/SimpleProject/displayuser")) {
			List<User> list = userservice.getAllRecords();
			json = new Gson().toJson(list);
			resp.setContentType("application/json");
            resp.getWriter().write(json);			
		}
		if (url.equals("/SimpleProject/changepassword")) {
			System.out.println("hello");
			user.setEmail(req.getParameter("email"));
			user.setPassword(req.getParameter("password"));
			int status= userservice.getUserByEmail(user);
			if(status==1) {
				req.getRequestDispatcher("/Login.jsp").forward(req, resp);
			}else {
				req.setAttribute("errormessage","There is some error in updating password...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp); 
			}
						
		}
		if (url.equals("/SimpleProject/checkemail")) {
			System.out.println(req.getParameter("email"));			
			  int status = userservice.usercheck(req.getParameter("email")); 
			  json = new  Gson().toJson(status); 
			  resp.setContentType("application/json");
			  resp.getWriter().write(json);
			 			
		}
		if (url.equals("/SimpleProject/displaystate")) {
			userstate.setCountry_name(req.getParameter("country"));
			List<UserState> statelist = null;
			if(userstate.getCountry_name()==null)
			{
				statelist = userstates.getAllRecords();
			}
			else {
				statelist = userstates.getAllRecordsByName(userstate);
			}			
			json = new Gson().toJson(statelist);
			resp.setContentType("application/json");
            resp.getWriter().write(json);			
		}
		if (url.equals("/SimpleProject/countryadd")) {	
			
			usercountry.setCountry_id(Integer.parseInt(req.getParameter("country_id")));
			usercountry.setCountry_name(req.getParameter("country"));
			int status=userCountrys.save(usercountry);
			if(status==1) {
				req.getRequestDispatcher("/addcountry.jsp").forward(req, resp);
			}else {
				req.setAttribute("errormessage","There is some error in insert country...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp); 
			}		
		}	
		if (url.equals("/SimpleProject/stateadd")) {
			userstate.setState_id(Integer.parseInt(req.getParameter("state_id")));
			userstate.setState_name(req.getParameter("state"));
			userstate.setCountry_name(req.getParameter("selectcountry"));
			int status=userstates.save(userstate);
			if(status==1) {
				req.getRequestDispatcher("/addstate.jsp").forward(req, resp);
			}else {
				req.setAttribute("errormessage","There is some error in insert country...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp); 
			}
		}
		if(url.equals("/SimpleProject/user")) {
			System.out.println(req.getParameter("abc"));
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			List<User> userlist=userservice.getAllRecords();
			req.setAttribute("userlist",userlist);
			req.getRequestDispatcher("user.jsp").forward(req, resp);
		}
		if (url.equals("/SimpleProject/login")) {
			user.setEmail(req.getParameter("email"));
			user.setPassword(req.getParameter("password"));
			int status=userservice.checkuser(user);
			
			if(status==1)
			{
				if(user.getRole().equals("admin"))
				{	
					session.setAttribute("user", user.getRole());
					session.setAttribute("userid", user.getId());
					session.setAttribute("username",user.getFname());
					session.setMaxInactiveInterval(3000);
					resp.sendRedirect("index.jsp");
				}
				else
				{
					session.setAttribute("user", user.getRole());
					session.setAttribute("userid", user.getId());
					session.setAttribute("username",user.getFname());
					System.out.println(session.getAttribute("normaluser"));
					session.setMaxInactiveInterval(3000);
					resp.sendRedirect("index.jsp");
				}						
			}
			else {
				req.setAttribute("errormessage","sorry username and password are wrong...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp);  
			}
		}
		if (url.equals("/SimpleProject/register")) {

			String name = req.getParameter("profile");
			String path = null;
			InputStream inputStream = null;
			String applicationPath = req.getServletContext().getRealPath("");
			// constructs path of the directory to save uploaded file
			String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
			// creates upload folder if it does not exists
			File uploadFolder = new File(uploadFilePath);
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}

			Part filePart = req.getPart("profile");
			if (filePart != null) {
				String fileName = filePart.getSubmittedFileName();
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());
				filePart.write(uploadFilePath + File.separator + fileName);
				path = uploadFilePath + File.separator + fileName;
			}
			File image = new File(path);
			inputStream = new FileInputStream(image);

			String hobby = "";
			String lang[] = req.getParameterValues("hobby");
			for (int i = 0; i < lang.length; i++) {
				hobby += lang[i] + " ";
			}
			
			int length=Integer.parseInt(req.getParameter("length"));
			length+=1;
			String[] address = new String[length];
			for (int i = 0; i < address.length; i++) {
				String addressname="address["+i+"][name]";
				String add=req.getParameter(addressname);				
					address[i]=add;
					System.out.println("address is"+i+":"+address[i]);						
			}	
			user.setId(Integer.parseInt(req.getParameter("user_id")));
			user.setFname(req.getParameter("fname"));
			user.setLname(req.getParameter("lname"));
			user.setEmail(req.getParameter("email"));
			user.setNumber(Long.parseLong(req.getParameter("number")));
			user.setPassword(req.getParameter("password"));						
			user.setGender(req.getParameter("gender"));
			user.setHobby(hobby);		
			user.setCountry(req.getParameter("country"));
			user.setState(req.getParameter("state"));
			user.setAddress(address);
			user.setDob(req.getParameter("birthdate"));
			
			int status = userservice.save(user, inputStream);
			if (status == 1) {
				int addressstatus=useraddress.save(user);
				if(addressstatus==1) {
					req.getRequestDispatcher("/Login.jsp").forward(req, resp);
				}else {
					req.setAttribute("errormessage", "sorry there is any error in additing address...");
					req.getRequestDispatcher("/error.jsp").include(req, resp);
				}			
			} else {
				req.setAttribute("errormessage", "sorry there is any error in additing records...");
				req.getRequestDispatcher("/error.jsp").include(req, resp);
			}
		}
		

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
