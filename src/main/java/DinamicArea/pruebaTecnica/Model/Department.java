package DinamicArea.pruebaTecnica.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptNo;

    @Column(name = "dept_name", nullable = false, unique = true, length = 40)
    private String deptName;
}
