package DinamicArea.pruebaTecnica.DTO;

import DinamicArea.pruebaTecnica.Model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private Long empNo;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private LocalDate hireDate;

    // Constructor para mapear de Employee a EmployeeDTO

}