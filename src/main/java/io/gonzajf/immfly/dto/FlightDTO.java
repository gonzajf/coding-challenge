package io.gonzajf.immfly.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDTO {

	private String ident;
	private String faFlightID;
	private String airline;

	@JsonProperty("airline_data")
	private String airlineData;
	
	@JsonProperty("flightnumber")
	private String flightNumber;
	
	@JsonProperty("tailnumber")
	private String tailNumber;
	
	private String type;
	private String codeshares;
	private boolean blocked;
	private boolean diverted;
	private boolean cancelled;
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public String getFaFlightID() {
		return faFlightID;
	}
	public void setFaFlightID(String faFlightID) {
		this.faFlightID = faFlightID;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getAirlineData() {
		return airlineData;
	}
	public void setAirlineData(String airlineData) {
		this.airlineData = airlineData;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getTailNumber() {
		return tailNumber;
	}
	public void setTailNumber(String tailNumber) {
		this.tailNumber = tailNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCodeshares() {
		return codeshares;
	}
	public void setCodeshares(String codeshares) {
		this.codeshares = codeshares;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public boolean isDiverted() {
		return diverted;
	}
	public void setDiverted(boolean diverted) {
		this.diverted = diverted;
	}
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}