package org.ariot.model;

import org.json.JSONObject;

public class SensorValues {

	String id;
	String iotPlatform;
	String content;
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getIotPlatform() {
		return iotPlatform;
	}



	public void setIotPlatform(String iotPlatform) {
		this.iotPlatform = iotPlatform;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}


	public SensorValues() {
		
		
	}

}
