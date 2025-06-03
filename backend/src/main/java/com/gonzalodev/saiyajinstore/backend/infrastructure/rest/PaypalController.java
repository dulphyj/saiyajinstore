package com.gonzalodev.saiyajinstore.backend.infrastructure.rest;

import com.gonzalodev.saiyajinstore.backend.application.PaypalService;
import com.gonzalodev.saiyajinstore.backend.config.AppUrlProperties;
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
@CrossOrigin(origins = "*")
@RequestMapping("/api/payments")
@Slf4j
@AllArgsConstructor
public class PaypalController {
    private final PaypalService paypalService;
    private final AppUrlProperties appUrlProperties;

    @PostMapping
    public URLPaypalResponse createPayment(@RequestBody DataPayment dataPayment) {
        String successUrl = appUrlProperties.getBackendBaseUrl() + "/api/payments/success";
        String cancelUrl = appUrlProperties.getBackendBaseUrl() + "/api/payments/cancel";
        try
            {
                Payment payment = paypalService.createPayment(
                    Double.valueOf(dataPayment.getAmount()),
                    dataPayment.getCurrency(),
                    dataPayment.getMethod(),
                    "SALE",
                    dataPayment.getDescription(),
                    cancelUrl,
                    successUrl
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
        return new URLPaypalResponse(appUrlProperties.getFrontendBaseUrl());
    }

    @GetMapping("/success")
    public RedirectView paymentSuccess(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try{
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                log.info("approved, success");
                return new RedirectView( appUrlProperties.getFrontendBaseUrl() + "/payment/success");
            }
        } catch (PayPalRESTException e) {
            log.warn("error with approved");
            e.printStackTrace();
        }
        return new RedirectView(appUrlProperties.getFrontendBaseUrl());
    }

    @GetMapping("/cancel")
    public RedirectView paymentCancel(){
        return new RedirectView(appUrlProperties.getFrontendBaseUrl());
    }
}
