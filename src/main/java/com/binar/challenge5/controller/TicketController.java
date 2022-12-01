package com.binar.challenge5.controller;

import com.binar.challenge5.entities.Ticket;
import com.binar.challenge5.service.Interface.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        Ticket ticket = new Ticket();
        try {
            ticket.setNameFile(multipartFile.getOriginalFilename());
            ticket.setFile(multipartFile.getBytes());
            String result = ticketService.uploadFile(ticket);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (IOException ioe) {
            return new ResponseEntity(ioe.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/download")
    public ResponseEntity downloadFile(@RequestParam("id") Long id) {
        Ticket ticket = ticketService.downloadFile(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        return new ResponseEntity(ticket.getFile(), HttpStatus.OK);
        return ResponseEntity.ok().headers(headers).body(ticket.getFile());
    }
}
