package io.gonzajf.immfly.util;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	public FlightDTO getFlightDetails(String tailNumber, String flightNumber) {
		
		FlightDTO[] response = restTemplate.getForObject(baseUrl+"/v1/flight-information/{tail-number}", FlightDTO[].class, tailNumber);
		
		Optional<FlightDTO> flightOptional = Stream.of(response)
												.filter(f -> f.getFlightNumber().equals(flightNumber)).findFirst();
		return flightOptional.orElse(null);
	}

}
