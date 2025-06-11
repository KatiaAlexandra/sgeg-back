package utez.edu.mx.sgeg.kernel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metadata {
    private int total;
    private int totalFiltered;
    private int currentPage;
    private int pageSize;
    private int totalPages;
}
