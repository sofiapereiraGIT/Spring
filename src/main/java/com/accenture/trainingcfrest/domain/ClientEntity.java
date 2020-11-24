package com.accenture.trainingcfrest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TRAININGCF_DB_CLIENT_TBLCLIENT")
public class ClientEntity {
	
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
	
	@Column(name = "FAMILYNAME")
	private String familyname;
	
	@Column(name = "AGE")
	private int age;
	
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
	
	public String getFamilyName() {
		return familyname;
	}
	public void setFamilyName(String familyname) {
		this.familyname = familyname;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	} 

}
