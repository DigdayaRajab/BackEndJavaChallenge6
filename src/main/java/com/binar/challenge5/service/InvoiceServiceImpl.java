package com.binar.challenge5.service;

import com.binar.challenge5.entities.Films;
import com.binar.challenge5.entities.Seats;
import com.binar.challenge5.entities.Users;
import com.binar.challenge5.model.CommonResponseGenerator;
import com.binar.challenge5.model.request.InvoiceRequest;
import com.binar.challenge5.model.request.SeatStatusRequest;
import com.binar.challenge5.model.response.FilmScheduleResponse;
import com.binar.challenge5.repositories.SeatsRepository;
import com.binar.challenge5.service.Interface.FilmService;
import com.binar.challenge5.service.Interface.InvoiceService;
import com.binar.challenge5.service.Interface.SeatsService;
import com.binar.challenge5.service.Interface.UsersService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    FilmService filmService;
    @Autowired
    UsersService usersService;
    @Autowired
    SeatsService seatsService;
    @Autowired
    SeatsRepository seatsRepository;
    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @Override
    public byte[] generateInvoice(HttpServletResponse response,
                                  @RequestBody InvoiceRequest invoiceRequest) throws Exception {
        JasperReport sourceFileName = JasperCompileManager.compileReport
                (ResourceUtils.getFile("src/main/resources/JasperInvoice.jrxml").getAbsolutePath());

        // creating our list of beans
        List<Map<String, String>> dataInvoice = new ArrayList<>();
        Map<String, String> data = new HashMap<>();

        Users users = usersService.searchUserById(invoiceRequest.getIdUser());
        Films films = filmService.findFilmsById(invoiceRequest.getIdFilm());
        List<FilmScheduleResponse> schedule = filmService.findFilmsScheduleByName(films.getFilmName());
        Seats seat = seatsRepository.findSeatById(invoiceRequest.getSeatNo(), invoiceRequest.getStudioName());

        FilmScheduleResponse filmScheduleRes = new FilmScheduleResponse();
        for (FilmScheduleResponse fs : schedule) {
            if ((invoiceRequest.getStartingHour().equals(fs.getStartingHour()) && (invoiceRequest.getShowDate().equals(fs.getShowDate())))) {
                filmScheduleRes.setFilmName(fs.getFilmName());
                filmScheduleRes.setStartingHour(fs.getStartingHour());
                filmScheduleRes.setTicketPrice(fs.getTicketPrice());
                filmScheduleRes.setShowDate(fs.getShowDate());
            }
        }

        if (filmScheduleRes.getFilmName() == null) {
            Log.info("error");
            throw new Exception("Data Film Not Found");
        }
        if (seat == null) {
            throw new Exception("Seat Not Available");
        }

        SeatStatusRequest seatStatusRequest = new SeatStatusRequest();
        seatStatusRequest.setSeatNo(seat.getSeatId().getSeatNo());
        seatStatusRequest.setStudioName(seat.getSeatId().getStudioName());
        seatsService.updateSeatsStatus(seatStatusRequest);

        data.put("userEmail", users.getEmail());
        data.put("userName", users.getUsername());
        data.put("filmName", filmScheduleRes.getFilmName());
        data.put("seatStudioName", String.valueOf(invoiceRequest.getStudioName()));
        data.put("seatNomor", String.valueOf(invoiceRequest.getSeatNo()));
        data.put("scheduleStart", filmScheduleRes.getStartingHour());
        data.put("schedulePrice", filmScheduleRes.getTicketPrice());
        data.put("scheduleDate", filmScheduleRes.getShowDate());
        data.put("TicketId", "No Ticket");
        dataInvoice.add(data);

        // creating datasource from bean list
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataInvoice);
        Map<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=InvoiceTicket.pdf;");

        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return new byte[0];
    }
}
