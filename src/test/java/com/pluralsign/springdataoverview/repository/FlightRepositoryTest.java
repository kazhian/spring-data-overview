package com.pluralsign.springdataoverview.repository;

import com.pluralsign.springdataoverview.entity.Flight;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class FlightRepositoryTest {
    @Autowired
    FlightRepository flightRepository;

        @Before("")
    public void deleteRecords() {
        flightRepository.deleteAll();
    }
    @Test
    public void shouldCrud() {
        final Flight flight = new Flight("Amsterdam", "New York", LocalDateTime.parse("2023-02-26T12:12:00"));

        flightRepository.save(flight);

        Assertions.assertThat(flightRepository.findAll()).hasSize(1).first().isEqualToComparingFieldByField(flight);

        flightRepository.delete(flight);
        Assertions.assertThat(flightRepository.count()).isZero();
    }

    @Test
    public void shouldFindFlightsFromLondon() {
            flightRepository.save(new Flight("London", "New York", LocalDateTime.parse("2023-02-26T12:12:00")));
            flightRepository.save(new Flight("London", "Amsterdam", LocalDateTime.parse("2023-02-26T12:12:00")));
            flightRepository.save(new Flight("Amsterdam", "New York", LocalDateTime.parse("2023-02-26T12:12:00")));

            List<Flight> fromLondon = flightRepository.findByOrigin("London");
            Assertions.assertThat(fromLondon).hasSize(2);

    }

    @Test
    public void shouldFindFlightsFromOrigins() {
        flightRepository.save(new Flight("London", "New York", LocalDateTime.parse("2023-02-26T12:12:00")));
        flightRepository.save(new Flight("Madrid", "Amsterdam", LocalDateTime.parse("2023-02-26T12:12:00")));
        flightRepository.save(new Flight("Amsterdam", "New York", LocalDateTime.parse("2023-02-26T12:12:00")));

        List<Flight> fromLondon = flightRepository.findByOriginIn("London", "Madrid");
        Assertions.assertThat(fromLondon).hasSize(2);

    }
}
