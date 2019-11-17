package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.Restclient;

public class GetAPITest extends TestBase {

	TestBase testbase;
	String serviceurl;
	String APIURL;
	String url;
	Restclient restclient;
	
	@BeforeMethod
	public void setup() {
		testbase= new TestBase();
		serviceurl=prop.getProperty("URL");
		APIURL=prop.getProperty("serviceurl");
		
		url= serviceurl +APIURL;
	}
	
	
	@Test
	public void getAPItest() throws ClientProtocolException, IOException {
	    restclient= new Restclient();
		restclient.get(url);
		
	}
	
	
	
	
}
