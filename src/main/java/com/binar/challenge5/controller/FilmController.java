package com.binar.challenge5.controller;

import com.binar.challenge5.entities.Films;
import com.binar.challenge5.model.CommonResponseGenerator;
import com.binar.challenge5.model.request.FilmRequest;
import com.binar.challenge5.model.request.FilmUpdateRequest;
import com.binar.challenge5.model.response.FilmScheduleResponse;
import com.binar.challenge5.service.Interface.FilmService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/film")
@SecurityRequirement(name = "bearerAuth")
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
    public ResponseEntity addFilm(@NonNull @RequestBody FilmRequest request) {
        try {
            Films response = filmService.save(request.getFilms());
            return new ResponseEntity(commonResponseGenerator.successResponse( response, "successful add data"), HttpStatus.OK);
       } catch (Exception e) {
            log.error("Add, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity updateFilm(@RequestBody FilmUpdateRequest paramUser) {
        try {
            FilmUpdateRequest response = filmService.updateFilm(paramUser);
            return new ResponseEntity(commonResponseGenerator.successResponse( response, "successful update data"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteFilm(@RequestParam("id_film") int idFilm) {
        try {
            filmService.deleteById(idFilm);
            return new ResponseEntity(commonResponseGenerator.successResponse( "", "successful delete data"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Delete, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/getAllFilms", produces = "application/json", consumes = "application/json")
    public ResponseEntity findAllFilms() {
        try {
            List<Films> response = filmService.findAll();

            HttpHeaders headers = new HttpHeaders();
            headers.add("content-type","application/json");
            return ResponseEntity.ok().headers(headers).body(commonResponseGenerator.successResponse( response, "ok"));
        } catch (Exception e) {
            log.error("Get All, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    //  All User Access
    @GetMapping(value = "/getFilmsShow", produces = "application/json", consumes = "application/json")
    public ResponseEntity findFilmsShow() {
        try {
            List<Films> response = filmService.findFilmsShow();

            HttpHeaders headers = new HttpHeaders();
            headers.add("content-type","application/json");
            return ResponseEntity.ok().headers(headers).body(commonResponseGenerator.successResponse( response, "ok"));
        } catch (Exception e) {
            log.error("Get Film Show, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getFilmsSchedule", produces = "application/json", consumes = "application/json")
    public ResponseEntity findFilmsScheduleByName(@RequestParam("film_name") String filmName) {
        try {
            List<FilmScheduleResponse> response = filmService.findFilmsScheduleByName(filmName);
            HttpHeaders headers = new HttpHeaders();
            headers.add("content-type","application/json");
            return ResponseEntity.ok().headers(headers).body(commonResponseGenerator.successResponse( response, "ok"));
        } catch (Exception e) {
            log.error("Get Schedule By Name, Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
