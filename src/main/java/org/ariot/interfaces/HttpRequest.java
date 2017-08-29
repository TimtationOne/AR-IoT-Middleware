package org.ariot.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.UUID;

import org.ariot.model.MappingData;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class HttpRequest {
	public String send(String restUrl, String contentType, String method, String body) throws IOException {
		String response="";
			URL url = new URL(restUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(method);// "POST");
			conn.setRequestProperty("Content-Type", contentType);// "application/vnd.onem2m-res+json;

			conn.setReadTimeout(0);


			OutputStream os = conn.getOutputStream();
			
			os.write(body.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED && conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != HttpURLConnection.HTTP_OK ) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			response = "Output from Server .... \n";
			while ((output = br.readLine()) != null) {
				response += output + "\n";
			}

			conn.disconnect();

		return response;
	}
	
	public String sendHRD(MappingData requestdata ) throws HttpServerErrorException, IOException {
		String response="";
		System.out.println("Debug: Request Data Url: "+requestdata.getUrl() );
		URL url = new URL(requestdata.getUrl());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);

		conn.setRequestMethod(requestdata.getMethod());
		System.out.println(requestdata.getHeaderReplaced());
		JSONObject header = new JSONObject(requestdata.getHeaderReplaced());
		Iterator<?> keys = header.keys();
		while( keys.hasNext() ) {
		    String key = (String)keys.next();
		    conn.setRequestProperty(key,header.getString(key));
		    System.out.println("DEBUG Request Header: "+key+":"+header.getString(key));
		}



		
		if (requestdata.getBodyReplaced()!=null && !(requestdata.getBodyReplaced().equals("")) ){
			System.out.println("DEBUG: Request has Body: "+requestdata.getBodyReplaced());
			OutputStream os = conn.getOutputStream();
			os.write(requestdata.getBodyReplaced().toString().getBytes());
			os.flush();
		}
		
		if (conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED && conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != HttpURLConnection.HTTP_OK ) {
			throw new HttpServerErrorException(HttpStatus.valueOf(conn.getResponseCode()), "HTTP Request Failed" );
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		while ((output = br.readLine()) != null) {
			response += output;
		}

		conn.disconnect();
		return response;
	}
	

}
