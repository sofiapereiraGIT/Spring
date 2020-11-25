package com.accenture.trainingcfrest.dto;

public class SalesOrderItemTO {
	
	private String id;
	private String createdAT;
	private String modifiedAT;
	private String createdBY;
	private String modifiedBY;
	private String status;
	private int quantity;
	private String salesOrderID; /*para nao dar recursivo no json por causa do ManyToOne e OneToMany*/
	private ProductsTO productID;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCreatedAT() {
        return createdAT;
    }
    public void setCreatedAT(String createdAT) {
        this.createdAT = createdAT;
    }

    public String getModifiedAT() {
        return modifiedAT;
    }
    public void setModifiedAT(String modifiedAT) {
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
	
	public String getSalesOrderID() {
		return salesOrderID;
	}
	public void setSalesOrderID(String salesOrderID) {
		this.salesOrderID = salesOrderID;
	}
	
	public ProductsTO getProductID() {
		return productID;
	}
	public void setProductID(ProductsTO productID) {
		this.productID = productID;
	}
}
