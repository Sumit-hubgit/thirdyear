package org.thirdyearproject.thirdyear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thirdyearproject.thirdyear.entity.Employee;
import org.thirdyearproject.thirdyear.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emp")
@CrossOrigin("http://localhost:5173/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping()
    public List<Employee>getAll(){
        return employeeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Employee> createEmp(@RequestBody Employee emp) {
        Employee tempEmp = employeeRepository.save(emp);
        if (tempEmp != null) {
            return new ResponseEntity<>(tempEmp, HttpStatus.OK); // Return the saved employee and status OK
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return an error status if saving fails
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable Long id, @RequestBody Employee updatedEmp) {
        // Find the employee by id
        Optional<Employee> existingEmp = employeeRepository.findById(id);

        if (existingEmp.isPresent()) {
            Employee emp = existingEmp.get();

            // Update employee details
            emp.setName(updatedEmp.getName());
            emp.setPhone(updatedEmp.getPhone());
            emp.setEmail(updatedEmp.getEmail());

            // Save the updated employee
            Employee savedEmp = employeeRepository.save(emp);

            return new ResponseEntity<>(savedEmp, HttpStatus.OK);
        } else {
            // Return 404 if the employee is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable Long id) {
        Optional<Employee> existingEmp = employeeRepository.findById(id);

        if (existingEmp.isPresent()) {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content indicates successful deletion
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);   // 404 Not Found if employee doesn't exist
        }
    }

}
