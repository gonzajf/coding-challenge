package io.gonzajf.immfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.gonzajf.immfly.domain.Flight;
import io.gonzajf.immfly.exception.FlightNotFoundException;
import io.gonzajf.immfly.service.FlightService;

@RestController
public class FlightController {
	
	private FlightService flightService;
	
	@Autowired
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping("/v1/flight-information/{tailNumber}/{flightNumber}")
	public Flight getFlight(@PathVariable String tailNumber, @PathVariable String flightNumber) {
		return flightService.getFlightDetails(tailNumber, flightNumber);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void flightNotFoundHandler(FlightNotFoundException ex) {}
	
}