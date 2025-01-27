package DinamicArea.pruebaTecnica.Controller;

import DinamicArea.pruebaTecnica.DTO.EmployeeDTO;
import DinamicArea.pruebaTecnica.Model.Employee;
import DinamicArea.pruebaTecnica.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;
    private final PagedResourcesAssembler<Employee> pagedResourcesAssembler;


    public EmployeeController(EmployeeService employeeService, PagedResourcesAssembler<Employee> pagedResourcesAssembler) {
        this.employeeService = employeeService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println("Datos enviados al cliente: " + employees); // Verifica aquí
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204
        }
        return ResponseEntity.ok(employees); // HTTP 200
    }


    @GetMapping("/test")
    public List<Employee> testGetEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
