package io.gonzajf.immfly.domain;

import java.io.Serializable;

public class Flight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5965244266367856471L;
	
	private String flightNumber;
	private String tailNumber;

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getTailNumber() {
		return tailNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public void setTailNumber(String tailNumber) {
		this.tailNumber = tailNumber;
	}
}