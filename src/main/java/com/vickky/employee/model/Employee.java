package com.vickky.employee.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document
public class Employee {
    @Id
    private String id;
    private String name;
    private String email;
    private String image;
    private LocalDateTime dateCreated;
    private String schedule;
    private String period;
    private Status status;
}
