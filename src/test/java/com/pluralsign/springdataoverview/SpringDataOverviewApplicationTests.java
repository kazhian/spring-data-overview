package com.pluralsign.springdataoverview;

import com.pluralsign.springdataoverview.entity.Flight;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
class SpringDataOverviewApplicationTests {

	@Autowired
	private EntityManager entityManager;

	@Test
	public void verifyFlightCanBeSaved() {
		final Flight flight = new Flight();
		flight.setOrigin("Amsterdam");
		flight.setDestination("New York");
		flight.setScheduledAt(LocalDateTime.parse("2023-02-26T12:12:00"));

		entityManager.persist(flight);
		final TypedQuery<Flight> results = entityManager.createQuery("select f from Flight f", Flight.class);
		final List<Flight> flights = results.getResultList();

		Assertions.assertThat(flights).hasSize(1).first().isEqualTo(flight);

	}

}
