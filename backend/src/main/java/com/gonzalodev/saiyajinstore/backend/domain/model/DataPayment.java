package com.gonzalodev.saiyajinstore.backend.domain.model;

import lombok.Data;

@Data
public class DataPayment {
    private String method;
    private String amount;
    private String currency;
    private String description;
}
