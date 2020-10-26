package io.gonzajf.immfly;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.gonzajf.immfly.controller.FlightController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getFlight_shouldReturnFlight() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/flight-information/EC-MYT/653"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.tailNumber").value("EC-MYT"))
				.andExpect(jsonPath("$.flightNumber").value("653"));		
	}
}