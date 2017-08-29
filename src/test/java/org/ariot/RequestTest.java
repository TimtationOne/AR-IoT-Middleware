package org.ariot;

import java.io.IOException;

import org.ariot.interfaces.RestEndpoint;
import org.ariot.logic.Mapper;
import org.ariot.model.SensorValues;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
public class RequestTest {
	/*@InjectMocks
	Mapper map = new Mapper();
	*/
	//@Mock
	RestEndpoint endpoint = new RestEndpoint();
	
	//@Test
	/*
	public void OverallFlowTest(){
		try {
			SensorValues sv = endpoint.getSensorValues("Weather_Stuttgart_2");
			System.out.println(sv.getContent() );
			assertEquals(sv.getIotPlatform(),"HPE UIoT Platform");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
