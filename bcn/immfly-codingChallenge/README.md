# Code Challenge

As a customer, we want a microservice that has the responsibility of retrieving all flight
information through an external service and storing it in a Redis database, so that we can
have a cache system to manage all flight information and use it in our other microservices.
Note that the external service should only have one endpoint that retrieves all flight
information based on the given tail number.
The tail number is the identification of an aircraft and an aircraft can perform several
flights in one day. It is the reason why the external service will return you a list of all the
flight information giving the tail number.  
The flight information is represented by this JSON:  

```json
[
	{
		"ident": "IBB653",
		"faFlightID": "IBB653-1581399936-airline-0136",
		"airline": "IBB",
		"airline_iata": "NT",
		"flightnumber": "653",
		"tailnumber": "EC-MYT",
		"type": "Form_Airline",
		"codeshares": "IBE123",
		"blocked": false,
		"diverted": false,
		"cancelled": false,
		"origin": {
			"code": "GCXO",
			"city": "Tenerife",
			"alternate_ident": "TFN",
			"airport_name": "TenerifeNorth(LosRodeos)"
		},
		"destination": {
			"code": "GCGM",
			"city": "LaGomera",
			"alternate_ident": "GMZ",
			"airport_name": "LaGomera"
		}
	},
	{
		"ident": "IBB652",
		"faFlightID": "IBB652-1581399936-airline-0136",
		"airline": "IBB",
		"airline_iata": "NT",
		"flightnumber": "652",
		"tailnumber": "EC-MYT",
		"type": "Form_Airline",
		"codeshares": "IBE122",
		"blocked": false,
		"diverted": false,
		"cancelled": false,
		"origin": {
			"code": "LEMD",
			"city": "Madrid",
			"alternate_ident": "MAD",
			"airport_name": "Adolfo Suárez Madrid-Barajas"
		},
		"destination": {
			"code": "LEBL",
			"city": "Barcelona",
			"alternate_ident": "BCN",
			"airport_name": "Aeropuerto Josep Tarradellas Barcelona-El Prat"
		}
	}
]
```


## Input  

We want an endpoint, for example: /v1/flight-information/EC-MYT/653
(/v1/flight-information/{tail-number}/{flight-number}) that retrieves the corresponding
flight information as a JSON.  

## Take into account

  - Regarding the external service, it can be taken as an axiom that the endpoint is
/v1/flight-information-tail/EC_MYT and this will retrieve an array's JSON with the
information of the flights.
  - Filtering must be done programmatically, not by querying the database.
  - It will be a good idea to use Docker for the Redis instance. There are a few
frameworks that you can use, for example, TestContainers.
  - The endpoint can only be used by an admin user.