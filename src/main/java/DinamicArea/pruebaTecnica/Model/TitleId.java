package DinamicArea.pruebaTecnica.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class TitleId implements Serializable {

    @JsonProperty("empNo")
    private Long empNo;

    @JsonProperty("title")
    private String title;

    @JsonProperty("fromDate")
    private LocalDate fromDate;

    // Constructor sin argumentos (obligatorio para Hibernate)
    public TitleId() {
    }

    // Constructor con todos los argumentos (opcional, para facilitar el uso)

    public TitleId(String title, LocalDate fromDate) {
        this.title = title;
        this.fromDate = fromDate;
    }

    public TitleId(Long empNo, String title, LocalDate fromDate) {
    }

    // Getters y Setters
    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    // Métodos equals() y hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleId titleId = (TitleId) o;
        return Objects.equals(empNo, titleId.empNo) &&
                Objects.equals(title, titleId.title) &&
                Objects.equals(fromDate, titleId.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, title, fromDate);
    }
}
