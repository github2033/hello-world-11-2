package react.support.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import react.support.dto.EmployeeDto;
import react.support.entity.Employee;
import react.support.repository.EmployeeRepository;
import react.support.util.EmployeeMapper;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto findById(Long employeeId)  {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found : " + employeeId));
        return EmployeeMapper.toEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employeeList = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return employeeList.stream().map(EmployeeMapper::toEmployeeDto).toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found : " + employeeId));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee = employeeRepository.save(employee);
        return EmployeeMapper.toEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found : " + employeeId);
        }
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.save(EmployeeMapper.toEmployee(employeeDto));
        return EmployeeMapper.toEmployeeDto(employee);
    }
}
