package org.example.hexlet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
    private String title;
    private String description;

    public Page() {
        title = "Hexlet Javalin Example";
        description = "It's my first web app";
    }
}
