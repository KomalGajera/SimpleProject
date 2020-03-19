package simpleproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import simpleproject.entitymodel.DataTableObject;
import simpleproject.entitymodel.User;
import simpleproject.service.UserService;
import simpleproject.service.UserServiceImpl;

public class UserDataController extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService userservice = new UserServiceImpl();
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		List<User> userlist=userservice.getAllRecords();
		req.setAttribute("userlist",userlist);
		DataTableObject dataTableObject = new DataTableObject();
		  dataTableObject.setAaData(userlist);
		   
		  Gson gson = new GsonBuilder().setPrettyPrinting().create();
		  String json = gson.toJson(dataTableObject);
		  out.print(json);
	}
}
