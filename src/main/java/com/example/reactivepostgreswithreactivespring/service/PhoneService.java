package com.example.reactivepostgreswithreactivespring.service;

import com.example.reactivepostgreswithreactivespring.model.Phone;
import com.example.reactivepostgreswithreactivespring.repository.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository phoneRepository;


    public Flux<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public Mono<Phone> getPhoneById(Long id) {
        return phoneRepository.findById(id);
    }

    public Mono<Phone> savePhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    public Mono<Void> deletePhoneById(Long id) {
        return phoneRepository.deleteById(id);
    }

    public Mono<Phone> updatePhone(Long id, Phone phone) {
        return phoneRepository.findById(id)
                .flatMap(p -> {
                    p.setBrand(phone.getBrand());
                    p.setModel(phone.getModel());
                    p.setOs(phone.getOs());
                    p.setPrice(phone.getPrice());
                    return phoneRepository.save(p);
                });
    }
}
