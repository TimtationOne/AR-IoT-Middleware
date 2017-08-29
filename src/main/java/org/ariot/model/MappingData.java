package org.ariot.model;

import java.util.Iterator;
import java.util.UUID;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "headerReplaced","bodyReplaced" })
public class MappingData {
	
	String deviceId;
	String platformName;
	String url;
	String header;
	String body;
	String method;
	String contentType;
	String filter;
	Boolean base64Encoded;
	int markerId;
	//String patternPath;
	
	public MappingData() {
		// TODO Auto-generated constructor stub
	}

	
	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public String getPlatformName() {
		return platformName;
	}


	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getHeader() {
		return header;
	}
	
	public String getHeaderReplaced() {
		if(header!=null){
			return replaceRandom(header);
		} else{
			return header;
		}
	}


	public void setHeader(String header) {
		this.header=header;
	}


	public String getBody() {
		return body;
	}
	
	public String getBodyReplaced() {
		if(body!=null){
			return replaceRandom(body);
		} else{
			return body;
		}
	}


	public void setBody(String body) {
		this.body = body;
	}
	
	private JSONObject replaceRandomJson(JSONObject object){
		Iterator<?> keys = object.keys();
		while( keys.hasNext() ) {
		    String key = (String)keys.next();
		    if(object.get(key).equals("#RANDOM#")){
		    	object.put(key, UUID.randomUUID().toString());
		    }
		}
		return object;
		
	}
	
	private String replaceRandom(String body){
		return body.replace("#RANDOM#", UUID.randomUUID().toString());
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public String getFilter() {
		return filter;
	}


	public void setFilter(String filter) {
		this.filter = filter;
	}


	public Boolean isBase64Encoded() {
		return base64Encoded;
	}


	public Boolean getBase64Encoded() {
		return base64Encoded;
	}


	public void setBase64Encoded(Boolean base64Encoded) {
		this.base64Encoded = base64Encoded;
	}


	public int getMarkerId() {
		return markerId;
	}


	public void setMarkerId(int markerId) {
		this.markerId = markerId;
	}

/*
	public String getPatternPath() {
		return patternPath;
	}


	public void setPatternPath(String patternPath) {
		this.patternPath = patternPath;
	}
	*/
	
	
	
	
	
}
