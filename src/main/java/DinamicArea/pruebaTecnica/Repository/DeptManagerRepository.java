package DinamicArea.pruebaTecnica.Repository;

import DinamicArea.pruebaTecnica.Model.DeptManager;
import DinamicArea.pruebaTecnica.Model.DetEmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptManagerRepository extends JpaRepository<DeptManager, Long>, JpaSpecificationExecutor<DeptManager> {
}
