package org.example.hexlet.dto.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.Car;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CarsPage extends BasePage {
    private List<Car> cars;
}
