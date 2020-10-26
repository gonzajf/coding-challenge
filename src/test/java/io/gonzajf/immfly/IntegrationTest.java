package io.gonzajf.immfly;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.gonzajf.immfly.domain.Flight;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getFlight_shouldReturnFlightDetails() throws Exception {
				
		ResponseEntity<Flight> response = restTemplate
				.getForEntity("/v1/flight-information/EC-MYT/653", Flight.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getTailNumber()).isEqualTo("EC-MYT");
		assertThat(response.getBody().getFlightNumber()).isEqualTo("653");
	}
}