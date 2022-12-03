package com.binar.challenge5.service.Interface;

import com.binar.challenge5.model.request.InvoiceRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;

@Service
public interface InvoiceService {
    byte[] generateInvoice(HttpServletResponse response, @RequestBody InvoiceRequest invoiceRequest) throws Exception;
}
