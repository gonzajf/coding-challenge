package io.gonzajf.immfly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

	@Cacheable(cacheNames = "flight", key="#tailNumber.concat('-').concat(#flightNumber)")
	public Flight getFlightDetails(String tailNumber, String flightNumber) {
		FlightDTO fligthDTO = flightClient.getFlightDetails(tailNumber, flightNumber);
		if(fligthDTO == null) {
			throw new FlightNotFoundException();
		}
		return FlightMapper.dtoToObject(fligthDTO);
	}

}
