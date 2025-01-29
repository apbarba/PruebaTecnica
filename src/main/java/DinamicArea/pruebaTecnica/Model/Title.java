package DinamicArea.pruebaTecnica.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.ALWAYS)
@Getter
@Setter
@Entity
@Builder
@Table(name = "titles")
public class Title {

    @EmbeddedId
    private TitleId id;

    @Column(name = "to_date", nullable = true)
    @JsonProperty("toDate")
    private LocalDate toDate;

    @ManyToOne
    @MapsId("empNo") // Vincula la propiedad "empNo" de TitleId con la relación de Employee
    @JoinColumn(name = "emp_no", nullable = false)
    @JsonProperty("employee")
    private Employee employee;


    // Constructor sin argumentos (obligatorio para Hibernate)
    public Title() {
    }

    // Constructor con argumentos
    public Title(TitleId id, LocalDate toDate, Employee employee) {
        this.id = id;
        this.toDate = toDate;
        this.employee = employee;
    }

    // Getters y Setters
    public TitleId getId() {
        return id;
    }

    public void setId(TitleId id) {
        this.id = id;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

