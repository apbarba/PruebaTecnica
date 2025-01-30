package DinamicArea.pruebaTecnica.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SalaryId implements Serializable {

    @JsonProperty("empNo")
    private Long empNo;

    @JsonProperty("fromDate")
    private LocalDate fromDate;

    public SalaryId() {
    }

    public SalaryId(Long empNo) {
    }
    public SalaryId(Long empNo, LocalDate fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }
}
