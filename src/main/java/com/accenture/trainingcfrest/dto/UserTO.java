package com.accenture.trainingcfrest.dto;

import java.util.Date;

public class UserTO {
	
	private String id;
	private Date createdAT;
	private Date modifiedAT;
	private String createdBY;
	private String modifiedBY;
	private String name;
	
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
}
