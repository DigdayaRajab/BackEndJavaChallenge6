package com.binar.challenge5.controller;

import com.binar.challenge5.entities.Schedules;
import com.binar.challenge5.model.CommonResponse;
import com.binar.challenge5.model.CommonResponseGenerator;
import com.binar.challenge5.model.request.SchedulesRequest;
import com.binar.challenge5.model.response.ScheduleResponse;
import com.binar.challenge5.service.Interface.ScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/schedules")
@SecurityRequirement(name = "bearerAuth")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
    public ResponseEntity addSchedules(@RequestBody SchedulesRequest schedules) {
        try {
            ScheduleResponse response = scheduleService.addSchedule(schedules);
            return new ResponseEntity(commonResponseGenerator.successResponse( response, "successful add data"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Created, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity updateSchedules(@RequestBody SchedulesRequest schedules) {
        try {
            ScheduleResponse response = scheduleService.updateFilm(schedules);
            return new ResponseEntity(commonResponseGenerator.successResponse( response, "successful update data"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteSchedules(@RequestParam("id") int idSchedule) {
        try {
            scheduleService.deleteSchedules(idSchedule);
            return new ResponseEntity(commonResponseGenerator.successResponse("", "successful delete data"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Delete, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "getSchedulesByFilmId")
    public ResponseEntity getSchedulesByFilmId(@RequestParam("id") int idFilm) {
        try {
            List<Schedules> response = scheduleService.findByFilmId(idFilm);
            return new ResponseEntity(commonResponseGenerator.successResponse( response, "ok"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "getAllSchedules")
    public ResponseEntity getAllSchedules() {
        try {
            List<Schedules> response = scheduleService.findAll();
            return new ResponseEntity(commonResponseGenerator.successResponse( response, "successful add data"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


}
