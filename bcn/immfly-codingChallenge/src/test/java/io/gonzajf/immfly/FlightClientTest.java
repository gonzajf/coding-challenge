package io.gonzajf.immfly;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.gonzajf.immfly.dto.FlightDTO;
import io.gonzajf.immfly.security.Profiles;
import io.gonzajf.immfly.util.FlightClient;

@Testcontainers
@SpringBootTest
@ActiveProfiles(Profiles.NO_AUTH)
public class FlightClientTest {

	@Autowired
	private FlightClient flightClient;
	
    private WireMockServer wireMockServer;    
    
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
	
    @BeforeEach
    void configureSystemUnderTest() {
      
    	wireMockServer = new WireMockServer(options()
                .port(8089));
        
        wireMockServer.start();
    }
    
    @Test
    public void shouldCallFlightServiceSuccessfully() throws Exception {
       
    	wireMockServer.stubFor(get(urlPathEqualTo("/v1/flight-information/EC-MYT"))
                .willReturn(aResponse()
                        .withBodyFile("flightsApiResponse.json")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        FlightDTO[] apiResponse = flightClient.getFlightDetails("EC-MYT");
        assertNotNull(apiResponse);
        assertEquals("IBB653-1581399936-airline-0136", apiResponse[0].getFaFlightID());
    }
}