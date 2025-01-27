package DinamicArea.pruebaTecnica.Repository;

import DinamicArea.pruebaTecnica.Model.DeptManager;
import DinamicArea.pruebaTecnica.Model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long>, JpaSpecificationExecutor<Title> {
}
