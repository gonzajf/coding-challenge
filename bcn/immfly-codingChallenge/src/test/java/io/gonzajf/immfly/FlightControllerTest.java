package io.gonzajf.immfly;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.gonzajf.immfly.controller.FlightController;
import io.gonzajf.immfly.domain.Flight;
import io.gonzajf.immfly.exception.FlightNotFoundException;
import io.gonzajf.immfly.security.JwtUtil;
import io.gonzajf.immfly.security.Profiles;
import io.gonzajf.immfly.service.FlightService;
import io.gonzajf.immfly.service.MyUserDetailsService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FlightController.class)
@ActiveProfiles(Profiles.NO_AUTH)
public class FlightControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FlightService flightService;
	
	@MockBean
	private MyUserDetailsService userService;

	@MockBean
	private JwtUtil jwtUtil;
	
	@Test
	public void getFlight_shouldReturnFlight() throws Exception {
		
		Flight flight = new Flight();
		flight.setTailNumber("EC-MYT");
		flight.setFlightNumber("653");
		
		given(flightService.getFlightDetails(anyString(), anyString())).willReturn(flight);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/flight-information/EC-MYT/653"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.tailNumber").value("EC-MYT"))
				.andExpect(jsonPath("$.flightNumber").value("653"));		
	}
	
	@Test
	public void getFlight_shouldReturnNotFound() throws Exception {
		given(flightService.getFlightDetails(anyString(), anyString())).willThrow(new FlightNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/flight-information/EC-MYT/653"))
				.andExpect(status().isNotFound());
	}
}