package com.accenture.trainingcfrest.dto;

import java.util.List;

public class SalesOrderTO {
	
	private String id;
	private String createdAT;
	private String modifiedAT;
	private String createdBY;
	private String modifiedBY;
	private String status;
	private UserTO userID;
	private ClientTO clientID;
	private List<SalesOrderItemTO> items;
	
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
	
	public UserTO getUserID() {
		return userID;
	}
	public void setUserID(UserTO userID) {
		this.userID = userID;
	}

	public ClientTO getClientID() {
		return clientID;
	}
	public void setClientID(ClientTO clientID) {
		this.clientID = clientID;
	}
	
	public List<SalesOrderItemTO> getItems() {
		return items;
	}
	public void setItems(List<SalesOrderItemTO> items) {
		this.items = items;
	}
}
