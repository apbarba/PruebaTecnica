package DinamicArea.pruebaTecnica.Controller;

import DinamicArea.pruebaTecnica.Model.Salary;
import DinamicArea.pruebaTecnica.Service.SalaryService;
import DinamicArea.pruebaTecnica.Specifications.SalarySpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

  /*  @GetMapping
    public ResponseEntity<List<Salary>> getAllEmployees() {
        List<Salary> employees = salaryService.getAllSalary();
        System.out.println("Datos enviados al cliente: " + employees);
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204
        }
        return ResponseEntity.ok(employees);
    } */

    @GetMapping
    public Page<Salary> getAllSalaries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer salaryFilter) {

        Pageable pageable = PageRequest.of(page, size);

        Specification<Salary> spec = Specification.where(SalarySpecification.filterBySalary(salaryFilter));

        return salaryService.getAllSalaries(spec, pageable);
    }


    @GetMapping("/{empNo}")
    public ResponseEntity<List<Salary>> getSalariesByEmpNo(@PathVariable Long empNo) {
        return ResponseEntity.ok(salaryService.getSalariesByEmpNo(empNo));
    }

    @PostMapping
    public ResponseEntity<Salary> createSalary(@RequestBody Salary salary) {
        if (salary == null || salary.getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Salary createdSalary = salaryService.createSalary(salary);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSalary);
    }

    @PutMapping("/{empNo}/{fromDate}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long empNo, @PathVariable String fromDate, @RequestBody Salary updatedSalary) {
        try {
            Salary salary = salaryService.updateSalary(empNo, LocalDate.parse(fromDate), updatedSalary);
            return ResponseEntity.ok(salary);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{empNo}/{fromDate}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long empNo, @PathVariable String fromDate) {
        LocalDate parsedFromDate = LocalDate.parse(fromDate);
        salaryService.deleteSalary(empNo, parsedFromDate);
        return ResponseEntity.noContent().build();
    }

}
