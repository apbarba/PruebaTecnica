package DinamicArea.pruebaTecnica.Controller;


import DinamicArea.pruebaTecnica.Model.Employee;
import DinamicArea.pruebaTecnica.Model.Title;
import DinamicArea.pruebaTecnica.Model.TitleId;
import DinamicArea.pruebaTecnica.Service.EmployeeService;
import DinamicArea.pruebaTecnica.Service.TitleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

    @Autowired
    private final TitleService titleService;
    private final EmployeeService employeeService;

    public TitleController(TitleService titleService, EmployeeService employeeService) {
        this.titleService = titleService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public Page<Title> getTitles(
            @RequestParam(value = "titleFilter", required = false) String titleFilter,
            @RequestParam(value = "empNoFilter", required = false) String empNoFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        // Llamamos al servicio para obtener los títulos filtrados y paginados
        return titleService.getTitlesWithFiltersAndPagination(titleFilter, empNoFilter, pageable);
    }
    @GetMapping("{id}")
    public ResponseEntity<List<Title>> getTitlesById(@PathVariable Long id) {
        List<Title> titles = titleService.getTitlesByEmpNo(id);
        if (titles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(titles);
    }

    @PostMapping
    public ResponseEntity<Title> createTitle(@RequestBody Map<String, Object> request) {
        // Extrae empNo del body (o el id como lo llameis)
        Long empNo = ((Number) request.get("empNo")).longValue();
        String title = (String) request.get("title");
        String fromDate = (String) request.get("fromDate"); // No puede ser nulo

        // Valida que el campo fromDate esté presente
        if (fromDate == null || fromDate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // O lanza un error con un mensaje específico
        }

        // Busca el empleado por empNo (o id)
        Employee employee = employeeService.getEmployeeById(empNo);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Crea un nuevo objeto Title y TitleId
        Title newTitle = new Title();
        newTitle.setEmployee(employee);
        newTitle.setToDate(null); // Asigna un valor predeterminado si no se envía
        TitleId titleId = new TitleId(title, LocalDate.parse(fromDate));
        newTitle.setId(titleId);

        // Guarda el nuevo título en la base de datos
        Title savedTitle = titleService.createTitle(newTitle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTitle);
    }

    @PutMapping("/{empNo}/{title}")
    public ResponseEntity<Title> updateTitle(@PathVariable Long empNo, @PathVariable String title, @RequestBody Title updatedTitle) {
        try {
            Title updatedEntity = titleService.updateTitle(empNo, title, updatedTitle);
            return ResponseEntity.ok(updatedEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTitleById(@PathVariable Long id) {
        try {
            titleService.deleteTitleByEmpNo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
