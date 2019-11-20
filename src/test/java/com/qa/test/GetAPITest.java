package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.mustache.Value;

import com.qa.base.TestBase;
import com.qa.client.Restclient;
import com.qa.util.testutil;

public class GetAPITest extends TestBase {

	TestBase testbase;
	String serviceurl;
	String APIURL;
	String url;
	Restclient restclient;
	CloseableHttpResponse closableHttpResponse;
	
	@BeforeMethod
	public void setup() {
		testbase= new TestBase();
		serviceurl=prop.getProperty("URL");
		APIURL=prop.getProperty("serviceurl");
		
		url= serviceurl +APIURL;
	}
	
	
	@Test
	public void getAPItestwithoutheader() throws ClientProtocolException, IOException {
	    restclient= new Restclient();
	    closableHttpResponse=restclient.get(url);
		
		//a. status code
		int statuscode =closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is:"+statuscode);
		
		Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//b.get json response
		String responsestring= EntityUtils.toString(closableHttpResponse.getEntity(),"UTF-8");
		JSONObject responsejson= new JSONObject(responsestring);
		System.out.println("JSON Response --->"+responsejson);
		
		
		//JSON OBject:-Per Page value
		String perpagevalue=testutil.getValueByJPath(responsejson, "/per_page");
		System.out.println("Per Page Value-->"+perpagevalue );
		Assert.assertEquals(Integer.parseInt(perpagevalue), 6);
		
		//Jsonobject:-Total Value
		
		String totalvalue=testutil.getValueByJPath(responsejson, "/total");
		System.out.println("total Value is-->"+totalvalue );
		Assert.assertEquals(Integer.parseInt(totalvalue), 12);
		
		
		//JsonArray
		String last_name=testutil.getValueByJPath(responsejson, "/data[0]/last_name");
		String id=testutil.getValueByJPath(responsejson, "/data[0]/id");
		String avatar=testutil.getValueByJPath(responsejson, "/data[0]/avatar");
		String first_name=testutil.getValueByJPath(responsejson, "/data[0]/first_name");
		String email=testutil.getValueByJPath(responsejson, "/data[0]/email");
		
		System.out.println("last_name is:-"+last_name);
		System.out.println("id is:-"+id);
		System.out.println("avatar is:-"+ avatar);
		System.out.println("first_name is :-"+first_name);
		System.out.println("email is :-"+email);

		//C. get all headers
		Header[] headersarray=closableHttpResponse.getAllHeaders();
		HashMap<String, String> allheaders= new HashMap<String, String>();
		for(Header header:headersarray) {
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("HeadersArray:--->"+allheaders);

	}
	
	@Test
	public void getAPItestwithheader() throws ClientProtocolException, IOException {
	    restclient= new Restclient();
	    
	    HashMap<String, String> headerMap= new HashMap<String, String>();
	    headerMap.put("Content-Type", "application/json");
	   /* headerMap.put("Username", "Tom");
	    headerMap.put("Pass", "1234");
	    headerMap.put("Auth Token", "1234");*/
	   
	    closableHttpResponse=restclient.get(url,headerMap);
		
		//a. status code
		int statuscode =closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is:"+statuscode);
		
		Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//b.get json response
		String responsestring= EntityUtils.toString(closableHttpResponse.getEntity(),"UTF-8");
		JSONObject responsejson= new JSONObject(responsestring);
		System.out.println("JSON Response --->"+responsejson);
		
		
		//JSON OBject:-Per Page value
		String perpagevalue=testutil.getValueByJPath(responsejson, "/per_page");
		System.out.println("Per Page Value-->"+perpagevalue );
		Assert.assertEquals(Integer.parseInt(perpagevalue), 6);
		
		//Jsonobject:-Total Value
		
		String totalvalue=testutil.getValueByJPath(responsejson, "/total");
		System.out.println("total Value is-->"+totalvalue );
		Assert.assertEquals(Integer.parseInt(totalvalue), 12);
		
		
		//JsonArray
		String last_name=testutil.getValueByJPath(responsejson, "/data[0]/last_name");
		String id=testutil.getValueByJPath(responsejson, "/data[0]/id");
		String avatar=testutil.getValueByJPath(responsejson, "/data[0]/avatar");
		String first_name=testutil.getValueByJPath(responsejson, "/data[0]/first_name");
		String email=testutil.getValueByJPath(responsejson, "/data[0]/email");
		
		System.out.println("last_name is:-"+last_name);
		System.out.println("id is:-"+id);
		System.out.println("avatar is:-"+ avatar);
		System.out.println("first_name is :-"+first_name);
		System.out.println("email is :-"+email);

		//C. get all headers
		Header[] headersarray=closableHttpResponse.getAllHeaders();
		HashMap<String, String> allheaders= new HashMap<String, String>();
		for(Header header:headersarray) {
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("HeadersArray:--->"+allheaders);

	}
	
	
	
	
	
	
	
	
	
}
