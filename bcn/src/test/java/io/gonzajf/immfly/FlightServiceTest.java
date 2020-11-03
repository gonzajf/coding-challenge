package io.gonzajf.immfly;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.gonzajf.immfly.domain.Flight;
import io.gonzajf.immfly.dto.FlightDTO;
import io.gonzajf.immfly.exception.FlightNotFoundException;
import io.gonzajf.immfly.service.FlightService;
import io.gonzajf.immfly.util.FlightClient;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

	@Mock
	private FlightClient flightClient;
	
	private FlightService flightService;
	
	@BeforeEach
	public void setUp() throws Exception {
		flightService = new FlightService(flightClient);
	}
	
	@Test
	public void getFlight_shouldReturnFlight() {
		
		FlightDTO flight = new FlightDTO();
		flight.setTailNumber("EC-MYT");
		flight.setFlightNumber("653");
		
		FlightDTO[] f = {flight};
		
		given(flightClient.getFlightDetails("EC-MYT")).willReturn(f);
		
		Flight flightDetails = flightService.getFlightDetails("EC-MYT", "653");
		
		assertThat(flightDetails.getTailNumber()).isEqualTo("EC-MYT");
		assertThat(flightDetails.getFlightNumber()).isEqualTo("653");
	}
	
	@Test
	public void getFlight_shouldReturnNotFound() {
		
		given(flightClient.getFlightDetails("EC-MYT")).willReturn(ArrayUtils.toArray());
		
		Assertions.assertThrows(FlightNotFoundException.class, 
				() -> flightService.getFlightDetails("EC-MYT", "653"));
	}
	
}