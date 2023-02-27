package com.pluralsign.springdataoverview.repository;

import com.pluralsign.springdataoverview.entity.Flight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Long> {

    List<Flight> findByOrigin(String london);
    List<Flight> findByOriginIn(String ... origins);
}
