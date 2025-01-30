package DinamicArea.pruebaTecnica.Specifications;

import DinamicArea.pruebaTecnica.Model.Title;
import org.springframework.data.jpa.domain.Specification;

public class TitleSpecification {

    public static Specification<Title> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null) {
                return criteriaBuilder.conjunction(); // Retorna un criterio vacío si no hay filtro
            }
            return criteriaBuilder.like(root.get("title"), "%" + title + "%"); // Asegúrate de que 'title' sea el nombre correcto del campo
        };
    }
}
