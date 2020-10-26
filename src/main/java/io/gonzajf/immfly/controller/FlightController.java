package io.gonzajf.immfly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.gonzajf.immfly.domain.Flight;
import io.gonzajf.immfly.service.FlightService;

@RestController
public class FlightController {
	
	private FlightService flightService;
	
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping("/v1/flight-information/{tailNumber}/{flightNumber}")
	public Flight getFlight(@PathVariable String tailNumber, @PathVariable String flightNumber) {
		return flightService.getFlightDetails(tailNumber, flightNumber);
	}
}