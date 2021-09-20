package ro.thales.mytools.registryapp.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemplateRequest implements Serializable {

    @NotNull(message = "requestById cannot be null")
    @Min(value=1, message="requestById must be equal or greater than 1")
    private Long requestById;

    @NotNull(message = "requestForId cannot be null")
    @Min(value=1, message="requestForId must be equal or greater than 1")
    private Long requestForId;

    @NotNull(message = "floorAccess cannot be null")
    @NotEmpty(message = "floorAccess cannot be empty")
    private int[] floorAccess;

    @NotNull(message = "kitRequired cannot be null")
    @NotEmpty(message = "kitRequired cannot be empty")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "kitRequired cannot be whitespaces")
    private String kitRequired;
}
