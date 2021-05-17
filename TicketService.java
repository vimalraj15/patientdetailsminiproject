	package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;

@Service
public class TicketService 
{
	@Autowired
	TicketRepository ticketrepository;
	
	public List<Ticket> getall()
	{
		List<Ticket> e = ticketrepository.findAll();		
		return e;			
	}
	
	public void  save(Ticket tickets )
	{
		ticketrepository.save(tickets);
					
	}
}
