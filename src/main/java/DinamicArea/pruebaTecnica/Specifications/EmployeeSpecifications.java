package DinamicArea.pruebaTecnica.Specifications;

import org.springframework.data.jpa.domain.Specification;
import DinamicArea.pruebaTecnica.Model.Employee;

public class EmployeeSpecifications {

    public static Specification<Employee> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstName"), firstName);
    }
}
