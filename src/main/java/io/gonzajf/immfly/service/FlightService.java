package io.gonzajf.immfly.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gonzajf.immfly.domain.Flight;
import io.gonzajf.immfly.dto.FlightDTO;
import io.gonzajf.immfly.exception.FlightNotFoundException;
import io.gonzajf.immfly.util.FlightClient;
import io.gonzajf.immfly.util.FlightMapper;

@Service
public class FlightService {
	
	private FlightClient flightClient;
	
	@Autowired
	public FlightService(FlightClient flieghtClient) {
		this.flightClient = flieghtClient;
	}

	public Flight getFlightDetails(String tailNumber, String flightNumber) {
		
		FlightDTO[] fligthDTO = flightClient.getFlightDetails(tailNumber);
		
		Optional<FlightDTO> optionalFlight = Stream.of(fligthDTO)
				.filter(f -> f.getFlightNumber().equals(flightNumber)).findFirst();
		
		if(optionalFlight.isEmpty()) {
			throw new FlightNotFoundException();
		}
		return FlightMapper.dtoToObject(optionalFlight.get());
	}
}
