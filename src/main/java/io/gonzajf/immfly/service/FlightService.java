package io.gonzajf.immfly.service;

import io.gonzajf.immfly.domain.Flight;
import io.gonzajf.immfly.dto.FlightDTO;
import io.gonzajf.immfly.util.FlightClient;
import io.gonzajf.immfly.util.FlightMapper;

public class FlightService {
	
	private FlightClient flightClient;
	
	public FlightService(FlightClient flieghtClient) {
		this.flightClient = flieghtClient;
	}

	public Flight getFlightDetails(String tailNumber, String flightNumber) {
		FlightDTO fligthDTO = flightClient.getFlightDetails(tailNumber, flightNumber);
		return FlightMapper.dtoToObject(fligthDTO);
	}

}
