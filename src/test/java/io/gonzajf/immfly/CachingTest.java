package io.gonzajf.immfly;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import io.gonzajf.immfly.dto.FlightDTO;
import io.gonzajf.immfly.service.FlightService;
import io.gonzajf.immfly.util.FlightClient;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CachingTest {

	@Autowired
	private FlightService service;

	@MockBean
	private FlightClient flightClient;

	@Container
	public static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:5.0.3-alpine"))
			.withExposedPorts(6379);

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
	public void getFlight_shouldReturnSecondTimeFromCache() {

		FlightDTO flight = new FlightDTO();
		flight.setTailNumber("EC-MYT");
		flight.setFlightNumber("653");

		given(flightClient.getFlightDetails(anyString(), anyString())).willReturn(flight);

		service.getFlightDetails("EC-MYT", "653");
		service.getFlightDetails("EC-MYT", "653");

		verify(flightClient).getFlightDetails("EC-MYT", "653");
	}
}