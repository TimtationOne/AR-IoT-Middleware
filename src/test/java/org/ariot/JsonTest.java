package org.ariot;

import static org.junit.Assert.*;

import org.ariot.model.MappingData;
import org.json.JSONObject;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonTest {
	public static void main(String[] args) {
		//replaceRandom
	}
	
/*	@Test
	public void replaceRandomTest(){
		MappingData fake = new MappingData();
		String header;
		fake.setUrl("https://quciuc.internetofthings.ibmcloud.com/api/v0002/device/types/Wettersensor/devices/Wetter_Stuttgart/events/status");
		fake.setMethod("GET");
		header = "{\"X-M2M-RI\":\"#RANDOM#\", \"X-M2M-Origin\":\"C0b96d81f-8c3ee7ad\", \"Authorization\":\"QzBiOTZkODFmLThjM2VlN2FkOkpRTklOR1ROWFg=\",\"Content-Type\":\"application/vnd.onem2m-res+json; ty=4\"}";
		//header = "{\"Content-Type\":\"application/json\",\"Authorization\":\"Basic YS1xdWNpdWMtZGVsM3Q5N3M4djpiUlVGd0dQUkErbEYoRnFTSFU=\"}";
		JSONObject test =  new JSONObject(header);
		fake.setHeader(test);;
		assertThat(fake.getHeader().getString("X-M2M-RI"),not("#RANDOM#"));
	}*/
}

