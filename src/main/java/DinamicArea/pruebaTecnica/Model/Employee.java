package DinamicArea.pruebaTecnica.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("empNo")  // Obligamos a Jackson a procesar este campo
    private Long empNo;

    @Column(name = "birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("birthDate") // Nombre personalizado en JSON
    private LocalDate birthDate;

    @Column(name = "first_name")
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("gender")
    private String gender;

    @Column(name = "hire_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("hireDate")
    private LocalDate hireDate;

}



