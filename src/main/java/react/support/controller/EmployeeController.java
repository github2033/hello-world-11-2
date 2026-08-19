package react.support.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import react.support.dto.EmployeeDto;
import react.support.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable("id") Long employeeId) {
        logger.info("findById(@PathVariable(\"id\") Long %s)".formatted(employeeId));

        return ResponseEntity.ok(employeeService.findById(employeeId));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        logger.info("findAllEmployees()");
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto employee) {
        logger.info("updateEmployee(@PathVariable(\"id\") Long %s, @RequestBody EmployeeDto employee) ".formatted(employeeId));
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employee));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto dto) {
        logger.info("addEmployee(@RequestBody EmployeeDto dto)");
        return ResponseEntity.ok(employeeService.addEmployee(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        logger.info("deleteEmployee(@PathVariable(\"id\") Long %s)".formatted(employeeId));
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Sucessfully deleted employee with id : " + employeeId);
    }
}
