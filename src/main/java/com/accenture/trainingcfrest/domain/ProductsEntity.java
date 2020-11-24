package com.accenture.trainingcfrest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TRAININGCF_DB_PRODUCTS_TBLPRODUCTS")
public class ProductsEntity {
	
	/** se a chave for composta temos de mandar esses 2 atributos para outra classe com a tag ?embeddable?
	 * e aqui fica só sem a tag ID e sem os gets, e metemos o get da class **/
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name = "CREATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAT;
    
    @Column(name = "MODIFIEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAT;
    
    @Column(name = "CREATEDBY")
    private String createdBY;
    
    @Column(name = "MODIFIEDBY")
    private String modifiedBY;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "MANUFACTOR")
	private String manufacturer;
	
	@Column(name = "SALESPRICE")
    private Double salesPrice;
	
	@Column(name = "BASEPRICE")
    private Double basePrice;
	
	@Column(name = "QUANTITY")
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
