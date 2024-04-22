package ru.edu.springdata.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Book {

    private Long id;
    private String name;
    private String language;
    private String category; // history, it, health etc...
}
