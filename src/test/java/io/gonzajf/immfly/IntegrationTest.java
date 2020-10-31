package io.gonzajf.immfly;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import io.gonzajf.immfly.domain.Flight;
import io.gonzajf.immfly.dto.FlightDTO;
import io.gonzajf.immfly.util.FlightClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
public class IntegrationTest {

	@Container
	public static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:5.0.3-alpine"))
		.withExposedPorts(6379);

	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private FlightClient flightClient;
	
	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.redis.host", redis::getHost);
		registry.add("spring.redis.port", redis::getFirstMappedPort);
	}

	@BeforeClass
	public void startContainer() {
		redis.start();
	}

	@AfterClass
	public void stopContainer() {
		redis.stop();
	}
	
	@Test
	public void getFlight_shouldReturnFlightDetails() throws Exception {
				
		FlightDTO flight = new FlightDTO();
		flight.setTailNumber("EC-MYT");
		flight.setFlightNumber("653");
		
		given(flightClient.getFlightDetails(anyString(), anyString())).willReturn(flight);
		
		ResponseEntity<Flight> response = restTemplate
				.getForEntity("/v1/flight-information/EC-MYT/653", Flight.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getTailNumber()).isEqualTo("EC-MYT");
		assertThat(response.getBody().getFlightNumber()).isEqualTo("653");
	}
}