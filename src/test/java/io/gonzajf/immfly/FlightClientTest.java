package io.gonzajf.immfly;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.gonzajf.immfly.dto.FlightDTO;
import io.gonzajf.immfly.util.FlightClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FlightClientTest {

	@Autowired
	private FlightClient flightClient;
	
    private WireMockServer wireMockServer;    
	
    @BeforeEach
    void configureSystemUnderTest() {
      
    	wireMockServer = new WireMockServer(options()
                .port(8089));
        
        wireMockServer.start();
    }
    
    @Test
    public void shouldCallWeatherService() throws Exception {
       
    	wireMockServer.stubFor(get(urlPathEqualTo("/v1/flight-information/EC-MYT"))
                .willReturn(aResponse()
                        .withBodyFile("flightsApiResponse.json")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        FlightDTO apiResponse = flightClient.getFlightDetails("EC-MYT", "653");
        assertNotNull(apiResponse);
    }
}
