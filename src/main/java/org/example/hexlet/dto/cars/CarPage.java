package org.example.hexlet.dto.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.Car;

@Getter
@Setter
@AllArgsConstructor
public class CarPage extends BasePage {
    private Car car;
}
