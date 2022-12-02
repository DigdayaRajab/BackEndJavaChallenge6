package com.binar.challenge5.controller;

import com.binar.challenge5.model.CommonResponse;
import com.binar.challenge5.model.CommonResponseGenerator;
import com.binar.challenge5.model.request.InvoiceRequest;
import com.binar.challenge5.service.Interface.InvoiceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api/invoice")
@SecurityRequirement(name = "bearerAuth")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    CommonResponseGenerator commonResponseGenerator;


    @PostMapping("/getInvoice")
    public ResponseEntity getInvoice(HttpServletResponse response, @RequestBody InvoiceRequest invoiceRequest) {
        try {
            invoiceService.generateInvoice(response, invoiceRequest);
        } catch (Exception e) {
            log.error("Generate , Error : " + e.getMessage());
            return new ResponseEntity(commonResponseGenerator.failedClientResponse("400", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
