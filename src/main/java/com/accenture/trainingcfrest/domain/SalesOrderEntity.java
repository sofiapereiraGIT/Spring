package com.accenture.trainingcfrest.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TRAININGCF_DB_SALESORDER_TBLSALESORDER")
public class SalesOrderEntity {
	
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
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserEntity userID;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private ClientEntity clientID;

	@OneToMany(mappedBy = "salesOrderID", cascade = { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH })
	private List<SalesOrderItemEntity> items;
	
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
	
	public UserEntity getUserID() {
		return userID;
	}
	public void setUserID(UserEntity userID) {
		this.userID = userID;
	}

	public ClientEntity getClientID() {
		return clientID;
	}
	public void setClientID(ClientEntity clientID) {
		this.clientID = clientID;
	}
	
	public List<SalesOrderItemEntity> getItems() {
		return items;
	}
	public void setItems(List<SalesOrderItemEntity> items) {
		this.items = items;
	}
}
