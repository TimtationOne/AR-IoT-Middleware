package org.ariot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ariot.model.MappingData;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeleteTest {

	public DeleteTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main (String[] args){
		//write();
		//read();
		String url = "http://uiot.westeurope.cloudapp.azure.com:8080/ariotmiddleware/sensorvalues/Weather_Stuttgart_1";
		String parts[] = url.split("/");
		String cleanUrl="";
		System.out.println("DEBUG Full url: "+url);
		for (int i=0;i<parts.length-2;i++){
			cleanUrl+=parts[i]+"/";
			System.out.println(parts[i]);
		}
		System.out.println("DEBUG Domain Name: "+cleanUrl);

		
	}
	
	public static void read(){
		//ArrayList<MappingData> fakes = new ArrayList<MappingData>(); 
		ObjectMapper mapper = new ObjectMapper();

		//JSON from file to Object
		try {
			ArrayList<MappingData> fakes  = mapper.readValue(new File("D:\\file.json"), new TypeReference<ArrayList<MappingData>>(){});
			System.out.println(fakes.get(0).getHeader()) ;
			System.out.println(fakes.get(0).getHeaderReplaced()) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//JSON from URL to Object
		//Staff obj = mapper.readValue(new URL("http://mkyong.com/api/staff.json"), Staff.class);

		//JSON from String to Object
		//Staff obj = mapper.readValue(jsonInString, Staff.class);
	}
	
	public static void write( ){
		ArrayList<MappingData> fakes = new ArrayList<MappingData>(); 
		MappingData fake = new MappingData();
		String header;
		fake.setDeviceId("Weather_Stuttgart_1");
		fake.setPlatformName("HPE UIoT");
		fake.setUrl("http://uiot.westeurope.cloudapp.azure.com:8080/HPE_IoT/Test01/default/latest");
		fake.setMethod("GET");
		header = "{\"X-M2M-RI\":\"#RANDOM#\", \"X-M2M-Origin\":\"C0b96d81f-8c3ee7ad\", \"Authorization\":\"QzBiOTZkODFmLThjM2VlN2FkOkpRTklOR1ROWFg=\",\"Content-Type\":\"application/vnd.onem2m-res+json; ty=4\"}";
		fake.setHeader(header);
		fake.setBase64Encoded(false);
		fake.setFilter("m2m:cin,con");
		fakes.add(fake);
		
		MappingData fake2 = new MappingData();
		fake.setDeviceId("Weather_Stuttgart_2");
		fake.setPlatformName("IBM Watson IoT");
		fake2.setUrl("https://quciuc.internetofthings.ibmcloud.com/api/v0002/device/types/Wettersensor/devices/Wetter_Stuttgart/events/status");
		fake2.setMethod("GET");
		String header2 = "{\"Content-Type\":\"application/json\",\"Authorization\":\"Basic YS1xdWNpdWMtZGVsM3Q5N3M4djpiUlVGd0dQUkErbEYoRnFTSFU=\"}";
		fake2.setHeader(header2);
		fake2.setBase64Encoded(true);
		fake2.setFilter("payload");
		fakes.add(fake2);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("D:\\file.json"), fakes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	public static void test1(){
		MappingData fake = new MappingData();
		String header;
		fake.setUrl("http://uiot.westeurope.cloudapp.azure.com:8080/HPE_IoT/Test01/default/latest");
		fake.setMethod("GET");
		header = "{\"X-M2M-RI\":\"#RANDOM#\", \"X-M2M-Origin\":\"C0b96d81f-8c3ee7ad\", \"Authorization\":\"QzBiOTZkODFmLThjM2VlN2FkOkpRTklOR1ROWFg=\",\"Content-Type\":\"application/vnd.onem2m-res+json; ty=4\"}";
		fake.setHeader(new JSONObject(header));
		fake.setBase64Encoded(false);
		fake.setFilter("m2m:cin,con");
		
		JSONObject fakeJSON = new JSONObject(fake);
		System.out.println(fakeJSON.toString());
	}*/
}
