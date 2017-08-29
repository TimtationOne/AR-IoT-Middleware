package org.ariot.logic;

import java.io.IOException;
import java.util.ArrayList;

import org.ariot.interfaces.HttpRequest;
import org.ariot.model.Device;
import org.ariot.model.MappingData;
import org.ariot.model.SensorValues;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class Mapper {
	@Autowired
	FileDBAccess fdb;
	
	MappingData requestdata;
	public Mapper() {
		// TODO Auto-generated constructor stub
	}
	public SensorValues getSensorValues(String deviceId){
		SensorValues sensorValues = new SensorValues();
		MappingData requestdata = fdb.getMappingData(deviceId);
		if (requestdata != null){
			sensorValues.setId(deviceId);
			sensorValues.setIotPlatform(requestdata.getPlatformName());
			sensorValues.setContent(getContent(requestdata));
		} else{
			sensorValues.setId("No Mapping Data found for "+deviceId);
		}
		return sensorValues;
		
	}
	
	private String getFileName(String path){
		String parts[] = path.split("/");
		if(parts.length>0 ){
			return parts[parts.length-1];
		} else{
			return "FileNotFound";
		}
	}
	
	public String getContent(MappingData mappingData){
		HttpRequest request = new HttpRequest();
		JSONObject responseJson;
		String reponseString;
		try {
			String response = request.sendHRD(mappingData);
			try {
				responseJson = new JSONObject(response);
				reponseString = filter(responseJson,mappingData);
			} catch (Exception e) {
				reponseString =  "{\"ERROR\":\"Wrong Response Format of IoT Platform: " +e.getMessage()+"\"}";
				responseJson = new JSONObject(reponseString);
			}
		} catch (HttpServerErrorException e) {
			reponseString =  "{\"ERROR\":\"Couldn't reach IoT Platform: " +e.getMessage()+"\"}";
			responseJson = new JSONObject(reponseString);
			e.printStackTrace();
		} catch (IOException e) {
			reponseString =  "{\"ERROR\":\"Invalid IoT Paltform Request Data: " +e.getMessage()+"\"}";
			responseJson = new JSONObject(reponseString);
			e.printStackTrace();
		}

		
		return reponseString;
	}
	
	private String filter(JSONObject unfilteredContent, MappingData requestdata){
		JSONObject filteredContent = unfilteredContent;
		String filterString = requestdata.getFilter();
		String[] filters = filterString.split(",");
		String content="";
		for (int i=0; i<filters.length; i++){
			if (i==filters.length-1){
				content = filteredContent.getString(filters[i]);
				if(requestdata.isBase64Encoded()){
					byte[] contentbytes = Base64Utils.decodeFromString(content);
					content = new String(contentbytes);
				}
			} else{
				filteredContent = filteredContent.getJSONObject(filters[i]);
			}
		}
		
		return content;
	}
	
	public ArrayList<Device> getDeviceList(String baseUrl){
		ArrayList<Device> deviceList = new ArrayList<Device>();
		ArrayList<MappingData> mappingDataList = fdb.getMappingDataList();
		for (int i=0;i<mappingDataList.size();i++){
			Device tempd = new Device();
			
			tempd.setDeviceId(mappingDataList.get(i).getDeviceId());
			System.out.println("DEBUG: Found Device "+tempd.getDeviceId() );
			tempd.setArMethod("Marker_Tracking");
			tempd.setMarkerId(mappingDataList.get(i).getMarkerId());
			deviceList.add(tempd);
		}
		return deviceList;
	}
	
	public ArrayList<Device> getDeviceList(){
		ArrayList<Device> deviceList = new ArrayList<Device>();
		ArrayList<MappingData> mappingDataList = fdb.getMappingDataList();
		for (int i=0;i<mappingDataList.size();i++){
			Device tempd = new Device();
			
			tempd.setDeviceId(mappingDataList.get(i).getDeviceId());
			System.out.println("DEBUG: Found Device "+tempd.getDeviceId() );
			tempd.setArMethod("Barcode_Tracking");
			tempd.setMarkerId(mappingDataList.get(i).getMarkerId());
			deviceList.add(tempd);
		}
		return deviceList;
	}
}
