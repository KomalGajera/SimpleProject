package simpleproject.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

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
	static final Logger LOGGER = Logger.getLogger(UserController.class);
	static{        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }

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
		
		
		/*This condition is fetch the country records..*/
		if (url.equals("/SimpleProject/displaycountry")) {
			LOGGER.info("processing of retrive country data");
			List<UserCountry> list = userCountrys.getAllRecords();
			json = new Gson().toJson(list);
			resp.setContentType("application/json");
            resp.getWriter().write(json);			
		}
		
		
		/*This condition is fetch the user record by id..*/
		if(url.equals("/SimpleProject/userbyid")) {
			LOGGER.info("processing of retrive user data by id");
			int id=Integer.parseInt(req.getParameter("id"));
			user=userservice.getRecordById(id);
			json = new Gson().toJson(user);
			resp.setContentType("application/json");
            resp.getWriter().write(json);			
		}
		
		
		/*This condition is fetch the user address record by id..*/
		if(url.equals("/SimpleProject/useraddress")) {
			LOGGER.info("processing of retrive user address data");
			int id=Integer.parseInt(req.getParameter("id"));
			System.out.println(id);
			List<User> list=useraddress.getRecordById(id);
			json = new Gson().toJson(list);
			resp.setContentType("application/json");
            resp.getWriter().write(json);			
		}
		
		
		/*This condition is fetch the user state record by id..*/
		if(url.equals("/SimpleProject/stateupdate")) {
			int id=Integer.parseInt(req.getParameter("id"));
			userstate=userstates.getRecordById(id);
			json = new Gson().toJson(userstate);
			resp.setContentType("application/json");
            resp.getWriter().write(json);			
		}
		
		
		/*This condition is fetch the user country record by id..*/
		if(url.equals("/SimpleProject/countryupdate")) {
			LOGGER.info("processing of retrive country data for upadte");
			int id=Integer.parseInt(req.getParameter("id"));
			usercountry=userCountrys.getRecordById(id);
			json = new Gson().toJson(usercountry);
			resp.setContentType("application/json");
            resp.getWriter().write(json);
		}
		
		
		/*This condition is delete particular state detail in list of state..*/
		if(url.equals("/SimpleProject/statedelete")) {
			LOGGER.info("processing of delete state data");
			int id=Integer.parseInt(req.getParameter("id"));
			int status=userstates.delete(id);
			json = new Gson().toJson(status);
			resp.setContentType("application/json");
            resp.getWriter().write(json);		
		}
		
		
		/*This condition is delete particular user detail and address delete by trigger*/
		if(url.equals("/SimpleProject/userdelete")) {
			LOGGER.info("processing of delete user data");
			int id=Integer.parseInt(req.getParameter("id"));
			int status=userservice.delete(id);
			json = new Gson().toJson(status);
			resp.setContentType("application/json");
            resp.getWriter().write(json);
		}
		
		
		/*This condition is delete particular country detail and with all state of this country..*/
		if(url.equals("/SimpleProject/countrydelete")) {
			LOGGER.info("processing of delete country data");
			int id=Integer.parseInt(req.getParameter("id"));
			int status=userCountrys.delete(id);	
			json = new Gson().toJson(status);
			resp.setContentType("application/json");
            resp.getWriter().write(json);	
		}
		
		
		/*This condition is update new password to particular user...*/
		if (url.equals("/SimpleProject/changepassword")) {
			LOGGER.info("processing of change password..");
			System.out.println("hello");
			user.setEmail(req.getParameter("email"));
			user.setPassword(req.getParameter("password"));
			int status= userservice.getUserByEmail(user);
			if(status==1) {
				LOGGER.info("change password successfully..");
				req.getRequestDispatcher("/Login.jsp").forward(req, resp);
			}else {
				LOGGER.error("error in changing password..");
				req.setAttribute("errormessage","There is some error in updating password...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp); 
			}
						
		}
		
		
		/*This condition is fetch the detail of user is exits or not..*/
		if (url.equals("/SimpleProject/checkemail")) {
			LOGGER.info("processing of check email when user is exits or not.");
			System.out.println(req.getParameter("email"));			
			  int status = userservice.usercheck(req.getParameter("email")); 
			  json = new  Gson().toJson(status); 
			  resp.setContentType("application/json");
			  resp.getWriter().write(json);			 			
		}
		
		
		/*This condition is fetch the state details..*/
		if (url.equals("/SimpleProject/displaystate")) {
			LOGGER.info("processing of retrive state data");
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
		
		
		/*This condition is add new country..*/
		if (url.equals("/SimpleProject/countryadd")) {	
			LOGGER.info("processing of add country data");
			usercountry.setCountry_id(Integer.parseInt(req.getParameter("country_id")));
			usercountry.setCountry_name(req.getParameter("country"));
			int status=userCountrys.save(usercountry);
			if(status==1) {
				LOGGER.info("add country successfully..");
				resp.sendRedirect("addcountry.jsp");
			}else {
				LOGGER.error("error in adding country..");
				req.setAttribute("errormessage","There is some error in insert country...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp); 
			}		
		}
		
		
		/*This condition is add new state.*/
		if (url.equals("/SimpleProject/stateadd")) {
			LOGGER.info("processing of add state data");
			userstate.setState_id(Integer.parseInt(req.getParameter("state_id")));
			userstate.setState_name(req.getParameter("state"));
			userstate.setCountry_name(req.getParameter("selectcountry"));
			int status=userstates.save(userstate);
			if(status==1) {
				LOGGER.info("add state detail successfully..");
				resp.sendRedirect("addstate.jsp");
			}else {
				LOGGER.error("error in addning state..");
				req.setAttribute("errormessage","There is some error in insert country...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp); 
			}
		}
		
		
		/*This condition is fetch the all user detail..*/
		if (url.equals("/SimpleProject/displayuser")) {
			LOGGER.info("processing of retrive user data");
			List<User> list = userservice.getAllRecords();
			json = new Gson().toJson(list);
			resp.setContentType("application/json");
            resp.getWriter().write(json);			
		}
		
		
		/*This condition is check user is valid or not..*/
		if (url.equals("/SimpleProject/login")) {
			
			LOGGER.info("processing of add user data to database");
			user.setEmail(req.getParameter("email"));
			user.setPassword(req.getParameter("password"));
			int status=userservice.checkuser(user);			
			if(status==1){
				if(user.getRole().equals("admin")){	
					session.setAttribute("user", user.getRole());
					session.setAttribute("userid", user.getId());
					session.setAttribute("username",user.getFname());
					session.setMaxInactiveInterval(3000);
					resp.sendRedirect("user.jsp");
				}else{
					session.setAttribute("user", user.getRole());
					session.setAttribute("userid", user.getId());
					session.setAttribute("username",user.getFname());
					session.setMaxInactiveInterval(3000);
					resp.sendRedirect("user.jsp");
				}						
			}else {
				LOGGER.error("error in login user..");
				req.setAttribute("errormessage","sorry username and password are wrong...");   
		        req.getRequestDispatcher("/error.jsp").include(req, resp);  
			}
		}		
		
		
		/*This condition is store new user record or update existing user records*/
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
						LOGGER.info("add user data successfully.");
						resp.sendRedirect("Login.jsp");
				}else {
					LOGGER.error("error in while adding address..");
					req.setAttribute("errormessage", "sorry there is any error in additing address...");
					req.getRequestDispatcher("/error.jsp").include(req, resp);
				}			
			} else {
				LOGGER.error("error in while adding user..");
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
