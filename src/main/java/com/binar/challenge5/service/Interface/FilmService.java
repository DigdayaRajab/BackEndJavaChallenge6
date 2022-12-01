package com.binar.challenge5.service.Interface;

import com.binar.challenge5.entities.Films;
import com.binar.challenge5.model.request.FilmUpdateRequest;
import com.binar.challenge5.model.response.FilmScheduleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmService {


    Films save(Films films);

    FilmUpdateRequest updateFilm(FilmUpdateRequest filmUpdateRequest) throws Exception;

    void deleteById(int idFilm);

    List<Films> findAll();

    List<Films> findFilmsShow() throws Exception;

    List<FilmScheduleResponse> findFilmsScheduleByName(String filmName) throws Exception;

    Films findFilmsById(Integer idFilm) throws Exception;
}
