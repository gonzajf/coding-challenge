package io.gonzajf.immfly.service;

import io.gonzajf.immfly.domain.Flight;
import io.gonzajf.immfly.util.FlightClient;

public class FlightService {
	
	private FlightClient flightClient;
	
	public FlightService(FlightClient flieghtClient) {
		this.flightClient = flieghtClient;
	}

	public Flight getFlightDetails(String tailNumber, String flightNumber) {
		return null;
	}

}
