package simpleproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import simpleproject.entitymodel.UserCountry;
import simpleproject.service.UserCountryService;
import simpleproject.service.UserCountryServiceImpl;

public class FtlHello extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setClassForTemplateLoading(FtlHello.class, "/");
		PrintWriter out=resp.getWriter();
		UserCountryService userCountrys = new UserCountryServiceImpl();
		try {
			Template template = configuration.getTemplate("freemaker.ftl");
			StringWriter writer = new StringWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			List<UserCountry> list = userCountrys.getAllRecords();
			map.put("countries", list);
			template.process(map, writer);
			out.print(writer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
}
