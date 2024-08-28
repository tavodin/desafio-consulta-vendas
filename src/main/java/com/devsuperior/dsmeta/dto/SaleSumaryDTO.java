package com.devsuperior.dsmeta.dto;

public class SaleSumaryDTO {

    private String sellerName;
    private Double total;

    public SaleSumaryDTO() {
    }

    public SaleSumaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
