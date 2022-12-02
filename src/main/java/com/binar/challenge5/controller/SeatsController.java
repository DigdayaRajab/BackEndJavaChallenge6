package com.binar.challenge5.controller;

import com.binar.challenge5.entities.Seats;
import com.binar.challenge5.model.CommonResponseGenerator;
import com.binar.challenge5.service.Interface.SeatsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/seats")
@SecurityRequirement(name = "bearerAuth")
public class SeatsController {

    @Autowired
    SeatsService seatsService;
    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
    public ResponseEntity addSeats(@RequestBody Seats seat) {
        try {
            Seats response = seatsService.newSeats(seat);
            return new ResponseEntity(commonResponseGenerator.successResponse(response, "successful add data"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity updateSeat(@RequestBody Seats seat) {
        try {
            Seats response = seatsService.updateSeats(seat);
            return new ResponseEntity(commonResponseGenerator.successResponse(response, "successful update data"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteSeat(@RequestBody Seats seat) {
        try {
            seatsService.deleteSeats(seat);
            return new ResponseEntity(commonResponseGenerator.successResponse("", "successful delete data"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "getAllSeats")
    public ResponseEntity getAll() {
        try {
            List<Seats> response = seatsService.findAllSeats();
            return new ResponseEntity(commonResponseGenerator.successResponse(response, "ok"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}



