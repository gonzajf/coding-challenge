package io.gonzajf.immfly.util;

import io.gonzajf.immfly.dto.FlightDTO;

public interface FlightClient {

	FlightDTO[] getFlightDetails(String tailNumber);
}
