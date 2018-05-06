package com.HNS.Entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class collaborators {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer collaboratorsId;
	private int storeId;//
	private int storeOwnerId;
	private int colloaboratorUserId;//
	private String colloabratorUserName;//
	public collaborators(int storeId, int storeOwnerId, int colloaboratorUserId,
			String colloabratorUserName) {
		super();
		this.storeId = storeId;
		this.storeOwnerId = storeOwnerId;
		this.colloaboratorUserId = colloaboratorUserId;
		this.colloabratorUserName = colloabratorUserName;
	}
	public collaborators()
	{
		super();
		this.storeId = 0;
		this.storeOwnerId = 0;
		this.colloaboratorUserId = 0;
		this.colloabratorUserName = "";
	}
	public Integer getCollaboratorsId() {
		return collaboratorsId;
	}
	public void setCollaboratorsId(Integer collaboratorsId) {
		this.collaboratorsId = collaboratorsId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getStoreOwnerId() {
		return storeOwnerId;
	}
	public void setStoreOwnerId(int storeOwnerId) {
		this.storeOwnerId = storeOwnerId;
	}
	public int getColloaboratorUserId() {
		return colloaboratorUserId;
	}
	public void setColloaboratorUserId(int colloaboratorUserId) {
		this.colloaboratorUserId = colloaboratorUserId;
	}

	public String getColloabratorUserName() {
		return colloabratorUserName;
	}
	public void setColloabratorUserName(String colloabratorUserName) {
		this.colloabratorUserName = colloabratorUserName;
	}
	
}
	
