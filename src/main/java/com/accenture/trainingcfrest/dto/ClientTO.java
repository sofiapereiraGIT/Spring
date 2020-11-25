package com.accenture.trainingcfrest.dto;

public class ClientTO {
	
	private String id;
	private String createdAT;
	private String modifiedAT;
	private String createdBY;
	private String modifiedBY;
	private String name;
	private String familyname;
	private int age;
	
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
