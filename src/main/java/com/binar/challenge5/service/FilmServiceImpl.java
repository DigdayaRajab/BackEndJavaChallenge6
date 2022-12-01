package com.binar.challenge5.service;

import com.binar.challenge5.entities.Films;
import com.binar.challenge5.model.request.FilmUpdateRequest;
import com.binar.challenge5.model.response.FilmScheduleResponse;
import com.binar.challenge5.repositories.FilmRepository;
import com.binar.challenge5.service.Interface.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmRepository filmRepository;

    @Override
    public Films save(Films films) {
        return filmRepository.save(films);
    }

    @Override
    public FilmUpdateRequest updateFilm(FilmUpdateRequest filmUpdateRequest) throws Exception {

        Films film = filmRepository.findFilmsById(filmUpdateRequest.getIdFilm());
        if (film == null) {
            throw new Exception("Film not Found");
        }

        try {
            filmRepository.update(
                    filmUpdateRequest.getFilmCode(),
                    filmUpdateRequest.getFilmName(),
                    filmUpdateRequest.getIsShow(),
                    filmUpdateRequest.getIdFilm());
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }

        FilmUpdateRequest newFilm = new FilmUpdateRequest();
        newFilm.setIdFilm(film.getIdFilm());
        newFilm.setFilmCode(film.getFilmCode());
        newFilm.setFilmName(film.getFilmName());
        newFilm.setIsShow(film.getIsShow());

        return newFilm;
    }

    @Override
    public void deleteById(int idFilm) {
        filmRepository.deleteById(idFilm);
    }

    @Override
    public List<Films> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public List<Films> findFilmsShow() throws Exception {
        List<Films> filmResponse = filmRepository.findFilmsShow();
        if (filmResponse == null) {
            throw new Exception("Films Showing Not Found");
        }
        return filmResponse;
    }

    @Override
    public List<FilmScheduleResponse> findFilmsScheduleByName(String filmName) throws Exception {
        List<FilmScheduleResponse> filmResponse = filmRepository.findFilmsScheduleByName(filmName);
        if (filmResponse == null || filmResponse.isEmpty()) {
            throw new Exception("Films Name Not Found");
        }
        return filmResponse;
    }

    @Override
    public Films findFilmsById(Integer idFilm) throws Exception {
        Films filmResponse = filmRepository.findFilmsById(idFilm);
        if (filmResponse == null) {
            throw new Exception("Film Not Found");
        }
        return filmResponse;
    }


}
