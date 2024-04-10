package com.example.reactivepostgreswithreactivespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    @Id
    private Long id;
    private String brand;
    private String model;
    private OperatingSystem os;
    private BigDecimal price;
}
