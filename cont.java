package com.example.demo;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class cont 
{   
	@RequestMapping("/")
	public String show()
	{
	   return "hi";
	}
	@RequestMapping("/y")
	public String filter()
	{
		return "FILTER";
	}
}
