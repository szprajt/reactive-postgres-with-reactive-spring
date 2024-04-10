package com.example.reactivepostgreswithreactivespring.repository;

import com.example.reactivepostgreswithreactivespring.model.Phone;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PhoneRepository extends ReactiveCrudRepository<Phone, Long> {
}
