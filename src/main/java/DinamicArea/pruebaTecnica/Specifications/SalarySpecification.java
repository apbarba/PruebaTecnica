package DinamicArea.pruebaTecnica.Specifications;

import DinamicArea.pruebaTecnica.Model.Salary;
import org.springframework.data.jpa.domain.Specification;

public class SalarySpecification {

    public static Specification<Salary> filterBySalary(Integer salary) {
        return (root, query, criteriaBuilder) -> {
            if (salary != null) {
                return criteriaBuilder.equal(root.get("salary"), salary);
            }
            return null;
        };
    }
}
