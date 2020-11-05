package io.gonzajf.immfly.util;

import org.springframework.stereotype.Component;

import io.gonzajf.immfly.domain.Flight;
import io.gonzajf.immfly.dto.FlightDTO;

@Component
public class FlightMapper {

	public static Flight dtoToObject(FlightDTO flightDTO) {

		Flight flight = new Flight();
		flight.setTailNumber(flightDTO.getTailNumber());
		flight.setFlightNumber(flightDTO.getFlightNumber());
		return flight;
	}
}