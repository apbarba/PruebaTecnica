package DinamicArea.pruebaTecnica.Model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class TitleId implements Serializable {
    private Long empNo;
    private String title;
    private LocalDate fromDate;

}
