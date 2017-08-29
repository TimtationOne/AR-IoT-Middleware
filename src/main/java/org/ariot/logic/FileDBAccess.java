package org.ariot.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.ariot.model.MappingData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FileDBAccess {
	@Value("${db.path}") 
	private String path;
	
	ArrayList<MappingData> mappingDataList = new ArrayList<MappingData>();
	
	public FileDBAccess() {
		// TODO Auto-generated constructor stub
	}
	
	public void writeToFile(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(path), mappingDataList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void readfromFile(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			mappingDataList  = mapper.readValue(new File(path), new TypeReference<ArrayList<MappingData>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void addMappingData(MappingData mappingData){
		this.mappingDataList.add(mappingData);
	}
	
	public MappingData getMappingData(String deviceId){
		//this.mappingDataList.add(mappingData);
		
		for (int i = 0; i < mappingDataList.size(); i++ ){
			if (mappingDataList.get(i).getDeviceId().equals(deviceId) ){
				System.out.println("Debug: Found Mapping Data for "+deviceId);
				return mappingDataList.get(i);
			}
		}
		System.out.println("Debug: Couldn't find Mapping Data for "+deviceId);
		return null;
	}
	
	public ArrayList<MappingData> getMappingDataList(){
		//this.mappingDataList.add(mappingData);
		
		return mappingDataList;
	}
	

}
