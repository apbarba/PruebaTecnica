package DinamicArea.pruebaTecnica.Model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class SalaryId implements Serializable {
    private Long empNo;
    private LocalDate fromDate;

}
