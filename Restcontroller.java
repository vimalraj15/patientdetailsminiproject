package com.example.demo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.demo.model.MainTable;
import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;

@RestController
public class Restcontroller {
	@Autowired
	private TicketService ticketservice;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = { "/login-user" }, method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String uname, @RequestParam String psw) throws SQLException {

		try {

			String sql = "SELECT name FROM up WHERE name=?";

			String username = (String) jdbcTemplate.queryForObject(sql, new Object[] { uname }, String.class);

			String sqll = "SELECT password FROM up WHERE password=?";

			String password = (String) jdbcTemplate.queryForObject(sqll, new Object[] { psw }, String.class);

			if (username.equals(uname) && password.equals(psw))
			{
				String recovered=   "select count(status) from patient where status='recoverd'";
				String death = "select count(status) from patient where status='death'";
				String treatment ="select count(status) from patient where status='treatment'";
				
				Integer recovered1	 = jdbcTemplate.queryForObject(recovered, Integer.class);
				Integer death1 = jdbcTemplate.queryForObject(death,Integer.class);
				Integer treatment1 = jdbcTemplate.queryForObject(treatment, Integer.class);
				
				int total = recovered1+ death1+treatment1;
				

				  ModelAndView m = new ModelAndView();
		           m.addObject("one",total);
		           m.addObject("two",death1);
		           m.addObject("three",recovered1);
		           m.addObject("four",treatment1);
		           m.setViewName("Change");
		   		   return m;
			} else {
				ModelAndView message = new ModelAndView();
				String g = "Invalid User name or password...Please check";

				message.addObject("vimal", g);

				message.setViewName("hi");
				return message;
			}
		} catch (Exception e) {
			ModelAndView message = new ModelAndView();
			String g = "Invalid Username or Password...please check";
			message.addObject("vimal", g);
			message.setViewName("hi");
			return message;
		}

	}

	@RequestMapping("/a")
	public ModelAndView Table() throws IOException, SQLException {

		ModelAndView m = new ModelAndView();
		m.setViewName("Form");
		return m;
	}	
	
	@RequestMapping(value = "/action", method = RequestMethod.POST)
	public ModelAndView newpatient(@RequestParam String name, @RequestParam String gender,
			@RequestParam String hospital, @RequestParam String addresss, @RequestParam String status)
	{
		
		String sqlInsert = "INSERT INTO patient (name,gender,hospital,address,status)" + " VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlInsert, name,gender,hospital,addresss,status);
		
		
		ModelAndView m = new ModelAndView();
	
		
		m.setViewName("sucess2");
		
		return m;
		
	}
	@RequestMapping(value = "/yf", method = RequestMethod.GET)
	public ModelAndView filter(@RequestParam String name) throws Exception {

		String k = "SELECT name FROM patient WHERE name=?";


		List<String> check = jdbcTemplate.query(k, new Object[] { name },
				BeanPropertyRowMapper.newInstance(String.class));
		
		int pq = check.size();

		
		
		if (pq > 0) {
			String sql = "SELECT name FROM patient WHERE name=?";

			String nam = (String) jdbcTemplate.queryForObject(sql, new Object[] {name }, String.class);

			String sql2 = "SELECT gender FROM patient WHERE name=?";

			String gender = (String) jdbcTemplate.queryForObject(sql2, new Object[] { name }, String.class);
		
			String sql3 = "SELECT hospital FROM patient WHERE name=?";

			String hospital = (String) jdbcTemplate.queryForObject(sql3, new Object[] { name }, String.class);

			String sql4 = "SELECT address FROM patient WHERE name=?";

			String  adddress = (String) jdbcTemplate.queryForObject(sql4, new Object[] { name }, String.class);

			String sql5 = "SELECT status FROM patient WHERE name=?";

			String  status = (String) jdbcTemplate.queryForObject(sql5, new Object[] { name }, String.class);


			ModelAndView m = new ModelAndView();
			m.addObject("v",nam);
			m.addObject("one",nam);
	           m.addObject("two",gender);
	           m.addObject("three",hospital);
	           m.addObject("four",adddress);
	           m.addObject("five",status);
			
			m.setViewName("Change2");
			
			return m;
			
			
			

		} else {
			ModelAndView s = new ModelAndView();
			String a = name + "     No patients...Check The Name..";
			s.addObject("vimal", a);
			s.setViewName("FILTER");
			return s;
		}
	}
	@RequestMapping(value = "/change-one", method = RequestMethod.GET)
	public ModelAndView changeone(@RequestParam String userchange, @RequestParam String data, @RequestParam String to)
			throws Exception {

		String sql = "update patient set " + userchange + " = '" + data + "' where name = +    '" + to + "' ";
		int check = jdbcTemplate.update(sql);

		
		if (check > 0) {
			ModelAndView m = new ModelAndView();
			m.addObject("vimal", "Values Updated Sucessfully");
			m.setViewName("FILTER");
			return m;
		} else {
			ModelAndView m = new ModelAndView();
			m.addObject("vimal", "Not Updated...Check The Values");
			m.setViewName("FILTER");
		}
		return null;

	}
	@RequestMapping(value = "/yd", method = RequestMethod.GET)
	public ModelAndView filterg(@RequestParam String name) throws Exception {

		String k = "SELECT name FROM patient WHERE name=?";


		List<String> check = jdbcTemplate.query(k, new Object[] { name },
				BeanPropertyRowMapper.newInstance(String.class));
		
		int pq = check.size();

		
		
		if (pq > 0) {
			String sql = "SELECT name FROM patient WHERE name=?";

			String nam = (String) jdbcTemplate.queryForObject(sql, new Object[] {name }, String.class);

			String sql2 = "SELECT gender FROM patient WHERE name=?";

			String gender = (String) jdbcTemplate.queryForObject(sql2, new Object[] { name }, String.class);
		
			String sql3 = "SELECT hospital FROM patient WHERE name=?";

			String hospital = (String) jdbcTemplate.queryForObject(sql3, new Object[] { name }, String.class);

			String sql4 = "SELECT address FROM patient WHERE name=?";

			String  adddress = (String) jdbcTemplate.queryForObject(sql4, new Object[] { name }, String.class);

			String sql5 = "SELECT status FROM patient WHERE name=?";

			String  status = (String) jdbcTemplate.queryForObject(sql5, new Object[] { name }, String.class);


			ModelAndView m = new ModelAndView();
			m.addObject("v",nam);
			m.addObject("one",nam);
	           m.addObject("two",gender);
	           m.addObject("three",hospital);
	           m.addObject("four",adddress);
	           m.addObject("five",status);
			
			m.setViewName("Change3");
			
			return m;
			
			
			

		} else {
			ModelAndView s = new ModelAndView();
			String a = name + "     No patients...Check The Name..";
			s.addObject("vimal", a);
			s.setViewName("FILTER");
			return s;
		}
	}
	
	@RequestMapping("/ag")
	public ModelAndView Tabled() throws IOException, SQLException {
		List<Ticket> h = ticketservice.getall();

		ModelAndView m = new ModelAndView();
		m.addObject("vimal", h);
		m.setViewName("Table");
		return m;
	}


}
