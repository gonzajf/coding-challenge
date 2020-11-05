package io.gonzajf.immfly.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.gonzajf.immfly.dto.FlightDTO;

@Component
public class FlightClientImpl implements FlightClient {

	@Value("${flights.url}")
	private String baseUrl;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public FlightClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Cacheable(cacheNames = "flight", key="#tailNumber")
	public FlightDTO[] getFlightDetails(String tailNumber) {
		
		ResponseEntity<FlightDTO[]> response = restTemplate.getForEntity(baseUrl+"/v1/flight-information/{tail-number}", FlightDTO[].class, tailNumber);
		
		if(response.getStatusCode().isError()) {
			throw new RuntimeException("An error occurred while trying to fetch fligths for tailnumber: "+tailNumber);
		}
		return response.getBody();
	}
}