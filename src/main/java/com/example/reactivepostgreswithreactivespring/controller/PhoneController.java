package com.example.reactivepostgreswithreactivespring.controller;

import com.example.reactivepostgreswithreactivespring.model.Phone;
import com.example.reactivepostgreswithreactivespring.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/phones")
@RequiredArgsConstructor
public class PhoneController {

     private final PhoneService phoneService;

     @GetMapping
     public Flux<Phone> getAllPhones() {
         return phoneService.getAllPhones();
     }

     @GetMapping("/{id}")
     public Mono<ResponseEntity<Phone>> getPhoneById(@PathVariable Long id) {
         return phoneService.getPhoneById(id)
                 .map(ResponseEntity::ok)
                 .defaultIfEmpty(ResponseEntity.notFound().build());
     }

     @PostMapping
     public Mono<Phone> savePhone(@RequestBody Phone phone) {
         return phoneService.savePhone(phone);
     }

     @PutMapping("/{id}")
     public Mono<Phone> updatePhone(@PathVariable Long id, @RequestBody Phone phone) {
         return phoneService.updatePhone(id, phone);
     }

     @DeleteMapping("/{id}")
     public Mono<Void> deletePhone(@PathVariable Long id) {
         return phoneService.deletePhoneById(id);
     }
}
