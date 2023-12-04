package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleSumaryDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public SaleSumaryDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public SaleSumaryDTO(Sale entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.amount = entity.getAmount();
        this.sellerName = entity.getSeller().getName();
    }


    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
