package DinamicArea.pruebaTecnica.Service;

import DinamicArea.pruebaTecnica.Model.Department;
import DinamicArea.pruebaTecnica.Model.Employee;
import DinamicArea.pruebaTecnica.Model.Title;
import DinamicArea.pruebaTecnica.Model.TitleId;
import DinamicArea.pruebaTecnica.Repository.EmployeeRepository;
import DinamicArea.pruebaTecnica.Repository.TitleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/title")
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;
    private EmployeeRepository employeeRepository;

    public TitleService(TitleRepository titleRepository, EmployeeRepository employeeRepository) {
        this.titleRepository = titleRepository;
        this.employeeRepository = employeeRepository;
    }


    // Obtener todos los títulos
    public List<Title> getAllTitles() {
        List<Title> titles = titleRepository.findAll();
        titles.forEach(title -> {
            if (title.getEmployee() == null) {
                throw new RuntimeException("Title sin empleado asociado: " + title);
            }
        });
        return titles;
    }

    public List<Title> getTitlesByEmpNo(Long empNo) {
        return titleRepository.findByIdEmpNo(empNo);
    }

    public Title createTitle(Title title) {
        return titleRepository.save(title);
    }

    // Guardar o actualizar un título
    public Title updateTitle(Long empNo, String title, Title updatedTitle) {
        Optional<Title> existingTitleOpt = titleRepository.findByIdEmpNoAndIdTitle(empNo, title);

        if (existingTitleOpt.isPresent()) {
            Title existingTitle = existingTitleOpt.get();

            // No modificar el ID ya existente
            updatedTitle.setId(existingTitle.getId());

            // Solo actualizar los valores permitidos
            existingTitle.setToDate(updatedTitle.getToDate());

            // Asegurar que el Employee es gestionado por Hibernate
            Employee existingEmployee = employeeRepository.findById(updatedTitle.getEmployee().getEmpNo())
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with empNo: " + updatedTitle.getEmployee().getEmpNo()));

            existingTitle.setEmployee(existingEmployee);

            // Guardar los cambios
            return titleRepository.save(existingTitle);
        } else {
            throw new EntityNotFoundException("Title not found for employee: " + empNo + " with title: " + title);
        }
    }


    public void deleteTitleByEmpNo(Long empNo) {
        List<Title> titles = titleRepository.findByIdEmpNo(empNo);

        if (titles.isEmpty()) {
            throw new RuntimeException("No titles found for employee number " + empNo);
        }

        titleRepository.delete(titles.get(0));
    }

}