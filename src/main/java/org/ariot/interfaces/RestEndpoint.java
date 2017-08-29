package org.ariot.interfaces;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.ariot.logic.FileDBAccess;
import org.ariot.logic.Mapper;
import org.ariot.model.Device;
import org.ariot.model.MappingData;
import org.ariot.model.SensorValues;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RestEndpoint {
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(RestEndpoint.class);
	@Autowired
	Mapper map;
	@Autowired
	FileDBAccess fdba;
	/*
	@Value("${upload.folder.path}") 
	private String saveDirectory = "/opt/ariotmiddleware/uploads/";
	*/

	@RequestMapping(value = { "/sensorvalues/{deviceId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET }, produces = "application/json")
	public @ResponseBody SensorValues getSensorValues(@PathVariable String deviceId) throws IOException {
		
		return map.getSensorValues(deviceId) ;
	}
	
	private String urlHelper(String url){
		String parts[] = url.split("/");
		String cleanUrl="";
		for (int i=0;i<parts.length-1;i++){
			cleanUrl+=parts[i]+"/";
		}
		System.out.println("DEBUG APP URL: "+cleanUrl);
		return cleanUrl;
	}
	
	@RequestMapping(value = { "/devices" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET }, produces = "application/json")
	public @ResponseBody ArrayList<Device> getDevices(HttpServletRequest request) throws IOException {
		String thisUrl = request.getRequestURL().toString();
		
		return map.getDeviceList();
	}
	
	
	@RequestMapping(value = { "/echo" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET }, produces = "application/json")
	public String echo() throws IOException {
		
		
		return "deployed";
	}
	
	@RequestMapping(value = "/devices/add", method = RequestMethod.GET)
	public String showDeviceForm(Model model) {
		model.addAttribute("deviceInfo", new MappingData());
		return "DeviceAddForm";
	}
	
	@RequestMapping(value = "/devices/add", method = RequestMethod.POST)
	public String addDevices(@ModelAttribute("deviceInfo") MappingData resultData,
			BindingResult result, Model model
			) throws IllegalStateException, IOException {
		
		/*
		if (fileUpload != null) {
				System.out.println("Saving file: " + fileUpload.getOriginalFilename());			
				if (!fileUpload.getOriginalFilename().equals("")) {
					fileUpload.transferTo(new File(saveDirectory + fileUpload.getOriginalFilename()));
					resultData.setPatternPath(saveDirectory + fileUpload.getOriginalFilename());
				}
		}*/

		fdba.addMappingData(resultData);
		fdba.writeToFile();
		model.addAttribute("message", "Congratulations you successfully added "+resultData.getDeviceId()+ "!");
		return "DeviceAddForm";
	}
	/*
	@RequestMapping(value = "/devices/add", method = RequestMethod.POST)
	public String addDevices(@ModelAttribute("deviceForm") MappingData deviceInfo,
			BindingResult result, Model model, @RequestParam CommonsMultipartFile fileUpload, HttpServletRequest request,
			@RequestParam String deviceId, @RequestParam String platformName, @RequestParam String url, @RequestParam String header, 
			@RequestParam String body, @RequestParam String method, @RequestParam String filter, @RequestParam boolean base64Encoded
			) throws IllegalStateException, IOException {
		//model.addAttribute("deviceInfo", deviceInfo.get );
		
		MappingData resultData = new MappingData();
		resultData.setDeviceId(deviceId);
		resultData.setPlatformName(platformName);
		resultData.setUrl(url);
		resultData.setHeader(header);
		resultData.setBody(body);
		resultData.setMethod(method);
		resultData.setFilter(filter);
		resultData.setBase64Encoded(base64Encoded);
		
		
		if (fileUpload != null) {
				System.out.println("Saving file: " + fileUpload.getOriginalFilename());			
				if (!fileUpload.getOriginalFilename().equals("")) {
					fileUpload.transferTo(new File(saveDirectory + fileUpload.getOriginalFilename()));
					resultData.setPatternPath(saveDirectory + fileUpload.getOriginalFilename());
				}
		}

		fdba.addMappingData(resultData);
		fdba.writeToFile();
		return "DeviceAddForm";
	} */
	
	/*
	@RequestMapping(value = "/files/{filename:.+}", method = RequestMethod.GET)
    public  @ResponseBody ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws MalformedURLException {
        Resource file = new UrlResource("file:"+saveDirectory+filename);
        if (file.exists() || file.isReadable()) {
        	return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
        return null;//ResponseEntity.notFound().header("Error=File Loading Error") ;
    }*/


}
