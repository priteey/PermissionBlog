package com.model;

import java.io.Serializable;

public class Directories implements Serializable {
	private int directoryId;
	private String directoryName;
	private String userId;
	private String accessType;
	private String changedAccessType;
	private String changedBy;

	
	public int getDirectoryId() {
		return directoryId;
	}
	public void setDirectoryId(int directoryId) {
		this.directoryId = directoryId;
	}
	public String getDirectoryName() {
		return directoryName;
	}
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getChangedAccessType() {
		return changedAccessType;
	}
	public void setChangedAccessType(String changedAccessType) {
		this.changedAccessType = changedAccessType;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

}
