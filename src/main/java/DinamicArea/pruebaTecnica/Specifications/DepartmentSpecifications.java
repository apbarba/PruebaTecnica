package DinamicArea.pruebaTecnica.Specifications;

import DinamicArea.pruebaTecnica.Model.Department;
import org.springframework.data.jpa.domain.Specification;

public class DepartmentSpecifications {

    public static Specification<Department> hasDeptName(String deptName) {
        return (root, query, criteriaBuilder) -> {
            if (deptName == null || deptName.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("deptName")), "%" + deptName.toLowerCase() + "%");
        };
    }
}
