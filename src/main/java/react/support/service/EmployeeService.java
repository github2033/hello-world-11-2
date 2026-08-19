package react.support.service;

import org.springframework.stereotype.Service;
import react.support.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    public EmployeeDto findById(Long employeeId);
    public List<EmployeeDto> findAll();
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    public void deleteEmployee(Long id);
    public EmployeeDto addEmployee(EmployeeDto employeeDto);
}
