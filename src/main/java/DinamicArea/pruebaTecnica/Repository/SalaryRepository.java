package DinamicArea.pruebaTecnica.Repository;

import DinamicArea.pruebaTecnica.Model.Salary;
import DinamicArea.pruebaTecnica.Model.SalaryId;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, SalaryId>, JpaSpecificationExecutor<Salary> {

    List<Salary> findByIdEmpNo(Long empNo);

    Optional<Salary> findById(SalaryId salaryId);
}
