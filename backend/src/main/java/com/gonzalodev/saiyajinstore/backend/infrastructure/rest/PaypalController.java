package com.gonzalodev.saiyajinstore.backend.infrastructure.rest;

import com.gonzalodev.saiyajinstore.backend.application.PaypalService;
import com.gonzalodev.saiyajinstore.backend.domain.model.DataPayment;
import com.gonzalodev.saiyajinstore.backend.domain.model.URLPaypalResponse;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/payments")
@Slf4j
@AllArgsConstructor
public class PaypalController {
    private final PaypalService paypalService;
    private final String SUCCESS_URL = "http://localhost:8080/api/payments/success";
    private final String CANCEL_URL = "http://localhost:8080/api/payments/cancel";

    @PostMapping
    public URLPaypalResponse createPayment(@RequestBody DataPayment dataPayment) {
        try
            {
                Payment payment = paypalService.createPayment(
                    Double.valueOf(dataPayment.getAmount()),
                    dataPayment.getCurrency(),
                    dataPayment.getMethod(),
                    "SALE",
                    dataPayment.getDescription(),
                    CANCEL_URL,
                    SUCCESS_URL
                );
                for(Links links: payment.getLinks()) {
                    if(links.getRel().equals("approval_url")){
                        log.info("approval done");
                        return new URLPaypalResponse(links.getHref());
                }
            }
            } catch (PayPalRESTException e){
            e.printStackTrace();
        }
        return new URLPaypalResponse("http://localhost:4200");
    }

    @GetMapping("/success")
    public RedirectView paymentSuccess(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try{
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                log.info("approved, success");
                return new RedirectView("http://localhost:4200/payment/success");
            }
        } catch (PayPalRESTException e) {
            log.warn("error with approved");
            e.printStackTrace();
        }
        return new RedirectView("http://localhost:4200");
    }

    @GetMapping("/cancel")
    public RedirectView paymentCancel(){
        return new RedirectView("http://localhost:4200");
    }
}
