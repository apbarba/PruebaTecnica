package DinamicArea.pruebaTecnica.Service;

import DinamicArea.pruebaTecnica.Model.Employee;
import DinamicArea.pruebaTecnica.Model.Salary;
import DinamicArea.pruebaTecnica.Model.SalaryId;
import DinamicArea.pruebaTecnica.Repository.EmployeeRepository;
import DinamicArea.pruebaTecnica.Repository.SalaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    public SalaryService(SalaryRepository salaryRepository, EmployeeRepository employeeRepository) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
    }

   /* public List<Salary> getAllSalary() {
        List<Salary> salaries = salaryRepository.findAll();
        salaries.forEach(salary -> {
            // Esto debería no fallar si el getter getEmployee() está presente
            System.out.println("Empleado recuperado: " + salary.getEmployee());
        });
        return salaries;
    } */

    public Page<Salary> getAllSalaries(Specification<Salary> spec, Pageable pageable) {
        return salaryRepository.findAll(spec, pageable);
    }
    public List<Salary> getSalariesByEmpNo(Long empNo) {
        return salaryRepository.findByIdEmpNo(empNo);
    }

    public Salary createSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    public Salary updateSalary(Long empNo, LocalDate fromDate, Salary updatedSalary) {
        SalaryId salaryId = new SalaryId(empNo, fromDate);

        Optional<Salary> existingSalaryOpt = salaryRepository.findById(salaryId);

        if (existingSalaryOpt.isPresent()) {
            Salary existingSalary = existingSalaryOpt.get();

            existingSalary.setSalary(updatedSalary.getSalary());

            if (updatedSalary.getEmployee() != null) {
                existingSalary.setEmployee(updatedSalary.getEmployee());
            }

            return salaryRepository.save(existingSalary);
        } else {
            throw new EntityNotFoundException("Salary not found for empNo: " + empNo + " and fromDate: " + fromDate);
        }
    }


    public void deleteSalary(Long empNo, LocalDate fromDate) {
        SalaryId salaryId = new SalaryId(empNo, fromDate);  // Usar el constructor de SalaryId con empNo y fromDate
        salaryRepository.deleteById(salaryId);  // Eliminar el salario con la clave compuesta
    }


}
