package DinamicArea.pruebaTecnica.Service;

import DinamicArea.pruebaTecnica.Model.Department;
import DinamicArea.pruebaTecnica.Model.Employee;
import DinamicArea.pruebaTecnica.Repository.DepartmentRepository;
import DinamicArea.pruebaTecnica.Specifications.DepartmentSpecifications;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

   /* public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    } */

    public Page<Department> getAllDepartmentsWithPaginationAndFilter(int page, int size, String deptName) {
        PageRequest pageRequest = PageRequest.of(page, size);  // Crea el PageRequest
        Specification<Department> spec = DepartmentSpecifications.hasDeptName(deptName); // Aplica la especificación
        return departmentRepository.findAll(spec, pageRequest);  // Pasa la especificación y el PageRequest
    }

    public Department getDepartmenteeById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id " + id));
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setDeptName(updatedDepartment.getDeptName());
                    return departmentRepository.save(department);
                }).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
