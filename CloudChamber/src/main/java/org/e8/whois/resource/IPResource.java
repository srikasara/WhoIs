package org.e8.whois.resource;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;



@Path("/whois")
public class IPResource {

	Map<String,String> mpCache;
	
	public IPResource(){
		
	}
	
public IPResource(Map<String,String> mpCache){
		this.mpCache=mpCache;
	}
	
	@GET
	@Path("/ip")
	//@Produces(MediaType.APPLICATION_XML)
	public String getWhoIsIP(@QueryParam("ip") String ipAddress){
		
		if(mpCache.containsKey(ipAddress))
		{
			return mpCache.get(ipAddress);
		}
		
		// Example for RestClient
		URI uri=URI.create("http://whois.arin.net/rest/ip/"+ipAddress);
		Client client=Client.create();
		WebResource webResource=client.resource(uri);
	//application/json
		String response = webResource.accept("application/xml").get(String.class);
		mpCache.put(ipAddress,response);
		System.out.println(response);
		return response;	
		
		
		
	}
}
