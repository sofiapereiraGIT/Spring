package com.accenture.trainingcfrest.dto;

import java.util.Date;

public class ProductsTO {
	
	private String id;
	private Date createdAT;
	private Date modifiedAT;
	private String createdBY;
	private String modifiedBY;
	private String name;
	private String manufacturer;
    private Double salesPrice;
    private Double basePrice;
    private int quantity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getCreatedAT() {
        return createdAT;
    }
    public void setCreatedAT(Date createdAT) {
        this.createdAT = createdAT;
    }

    public Date getModifiedAT() {
        return modifiedAT;
    }
    public void setModifiedAT(Date modifiedAT) {
        this.modifiedAT = modifiedAT;
    }

    public String getCreatedBY() {
        return createdBY;
    }
    public void setCreatedBY(String createdBY) {
        this.createdBY = createdBY;
    }

    public String getModifiedBY() {
        return modifiedBY;
    }
    public void setModifiedBY(String modifiedBY) {
        this.modifiedBY = modifiedBY;
    }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public Double getSalesPrice() {
        return salesPrice;
    }
    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }
    
    public Double getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}