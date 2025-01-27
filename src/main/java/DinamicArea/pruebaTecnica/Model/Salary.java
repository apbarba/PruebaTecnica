package DinamicArea.pruebaTecnica.Model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "salary")
public class Salary {

    @EmbeddedId
    private SalaryId id;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @ManyToOne
    @MapsId("empNo")
    @JoinColumn(name = "emp_no", nullable = false)
    private Employee employee;
}

