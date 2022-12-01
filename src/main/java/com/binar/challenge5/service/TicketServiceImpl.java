package com.binar.challenge5.service;

import com.binar.challenge5.entities.Ticket;
import com.binar.challenge5.repositories.TicketRepository;
import com.binar.challenge5.service.Interface.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public String uploadFile(Ticket ticket){
        try{
            ticketRepository.save(ticket);
            return "upload success";
        }catch(Exception e){
           log.error("Error has been found because : {}", e.getMessage());
            return e.getMessage();
        }

    }

    @Override
    public Ticket downloadFile(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

}
