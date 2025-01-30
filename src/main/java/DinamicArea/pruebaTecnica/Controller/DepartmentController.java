package DinamicArea.pruebaTecnica.Controller;

import DinamicArea.pruebaTecnica.Model.Department;
import DinamicArea.pruebaTecnica.Model.Employee;
import DinamicArea.pruebaTecnica.Service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

   /* @GetMapping
    public List<Department> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        System.out.println("Departments found: " + departments);
        return departments;
    } */

    @GetMapping
    public Page<Department> getAllDepartments(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String deptName // deptName es opcional
    ) {
        Page<Department> departments = departmentService.getAllDepartmentsWithPaginationAndFilter(page, size, deptName);
        System.out.println("Departments found: " + departments);
        return departments;
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmenteeById(id);
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        return departmentService.updateDepartment(id, updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}

