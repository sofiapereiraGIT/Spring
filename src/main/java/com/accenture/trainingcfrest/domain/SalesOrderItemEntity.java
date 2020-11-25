package com.accenture.trainingcfrest.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TRAININGCF_DB_SALESORDER_TBLSALESORDERITEM")
public class SalesOrderItemEntity {
	
	/** se a chave for composta temos de mandar esses 2 atributos para outra classe com a tag ?embeddable?
	 * e aqui fica só sem a tag ID e sem os gets, e metemos o get da class **/
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name = "CREATEDAT")
    private LocalDateTime createdAT;
    
    @Column(name = "MODIFIEDAT")
    private LocalDateTime modifiedAT;
    
    @Column(name = "CREATEDBY")
    private String createdBY;
    
    @Column(name = "MODIFIEDBY")
    private String modifiedBY;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "SALESORDER_ID")
	private SalesOrderEntity salesOrderID;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private ProductsEntity productID;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public LocalDateTime getCreatedAT() {
        return createdAT;
    }
    public void setCreatedAT(LocalDateTime createdAT) {
        this.createdAT = createdAT;
    }

    public LocalDateTime getModifiedAT() {
        return modifiedAT;
    }
    public void setModifiedAT(LocalDateTime modifiedAT) {
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
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public SalesOrderEntity getSalesOrderID() {
		return salesOrderID;
	}
	public void setSalesOrderID(SalesOrderEntity salesOrderID) {
		this.salesOrderID = salesOrderID;
	}
	
	public ProductsEntity getProductID() {
		return productID;
	}
	public void setProductID(ProductsEntity productID) {
		this.productID = productID;
	}
	
}
