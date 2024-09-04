package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    private Long id;
    private String make;
    private String model;

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
    }
}
