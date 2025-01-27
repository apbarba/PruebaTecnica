package DinamicArea.pruebaTecnica.Service;

import DinamicArea.pruebaTecnica.Model.Employee;
import DinamicArea.pruebaTecnica.Repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        System.out.println("Found employees: " + employees.getContent());
        return employees;
    }

    @PostConstruct
    public void testRepository() {
        List<Employee> employees = employeeRepository.findAll();
        System.out.println("Empleados encontrados: " + employees);
    }
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(employee -> System.out.println("Empleado recuperado: " + employee));
        return employees;
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id) {
        Employee employee = getEmployeeById(id);

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}

