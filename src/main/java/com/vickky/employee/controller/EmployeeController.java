package com.vickky.employee.controller;

import com.vickky.employee.model.Employee;
import com.vickky.employee.model.Response;
import com.vickky.employee.model.Status;
import com.vickky.employee.repository.EmployeeRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Response> getEmployees() {
        return ResponseEntity.ok(
                Response.builder().dateTime(LocalDateTime.now())
                        .data(Map.of("Employee", repository.findAll()))
                        .message("Employee retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getEmployeeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(
                Response.builder()
                        .dateTime(LocalDateTime.now())
                        .data(Map.of("Employee", repository.findById(id)))
                        .message("Employee retrieved").status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build());
    }

    @GetMapping("/search/{value}")
    public ResponseEntity<Response> searchEmployeeByName(@PathVariable("value") String value) {
     Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(value));

        return ResponseEntity.ok(
                Response.builder()
                        .dateTime(LocalDateTime.now())
                        .data(Map.of("Employee", query))
                        .message("Employee retrieved").status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build());
    }


    @PostMapping
    public ResponseEntity<Response> createEmployee(@RequestBody Employee employee) {
        employee.setDateCreated(LocalDateTime.now());
        employee.setStatus(Status.INACTIVE);
        return ResponseEntity.ok(
                Response.builder().dateTime(LocalDateTime.now())
                        .data(Map.of("Employee", repository.save(employee)))
                        .message("Employee created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteEmployeeById(@PathVariable("id") String id) {
            repository.deleteById(id);
            return ResponseEntity.ok(
                    Response.builder()
                            .dateTime(LocalDateTime.now())
                            .data(null)
                            .message("Employee deleted").status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value()).build());
        }
    @PutMapping()
    public ResponseEntity<Response> updateEmployee(@RequestBody Employee employee) {
       Status status= repository.findById(employee.getId()).get().getStatus();
       employee.setStatus(status);
        employee.setDateCreated(LocalDateTime.now());
        return ResponseEntity.ok(
                Response.builder()
                        .dateTime(LocalDateTime.now())
                        .data(Map.of("Employee",repository.save(employee)))
                        .message("Employee updated").status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build());
    }

}
