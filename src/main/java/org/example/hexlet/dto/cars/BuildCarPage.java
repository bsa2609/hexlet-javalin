package org.example.hexlet.dto.cars;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hexlet.dto.BasePage;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildCarPage extends BasePage {
    private String make;
    private String model;
    private Map<String, List<ValidationError<Object>>> errors;
}
