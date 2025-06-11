package utez.edu.mx.sgeg.kernel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T> {

    private T result;

    private Metadata metadata;

    private TypesResponse type;

    private String text;

    public ApiResponse(T result, TypesResponse type, String text) {
        this.result = result;
        this.type = type;
        this.text = text;
    }

    public ApiResponse(TypesResponse type, String text) {
        this.type = type;
        this.text = text;
    }

}