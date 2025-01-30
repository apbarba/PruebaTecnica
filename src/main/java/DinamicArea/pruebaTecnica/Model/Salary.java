package DinamicArea.pruebaTecnica.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "salaries")  // Asegúrate de usar "salaries" en lugar de "salary"
public class Salary {

    @EmbeddedId
    private SalaryId id; // La clave compuesta

    @Column(name = "salary", nullable = false)
    @JsonProperty("salary")
    private Integer salary;

    @ManyToOne
    @MapsId("empNo")
    @JoinColumn(name = "emp_no", nullable = false)
    @JsonProperty("employee")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }
    public SalaryId getId() {
        return id;
    }

    public Integer getSalary() {
        return salary;  // Asegúrate de que este método existe.
    }

    public void setSalary(Integer salary) {
        this.salary = salary;  // Asegúrate de que este método también existe.
    }

    // También puedes incluir el getter y setter de employee si no usas Lombok.
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}


