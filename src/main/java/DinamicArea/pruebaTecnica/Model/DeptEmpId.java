package DinamicArea.pruebaTecnica.Model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DeptEmpId implements Serializable {
    private Long empNo;
    private Long deptNo;


}
