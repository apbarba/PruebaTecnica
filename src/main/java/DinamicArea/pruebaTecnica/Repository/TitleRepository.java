package DinamicArea.pruebaTecnica.Repository;

import DinamicArea.pruebaTecnica.Model.DeptManager;
import DinamicArea.pruebaTecnica.Model.Title;
import DinamicArea.pruebaTecnica.Model.TitleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, TitleId>, JpaSpecificationExecutor<Title> {


    List<Title> findByIdEmpNo(Long empNo);

    Optional<Title> findByIdEmpNoAndIdTitle(Long empNo, String title);

}
