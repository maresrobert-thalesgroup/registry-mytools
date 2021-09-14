package ro.thales.mytools.registryapp.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemplateRequest implements Serializable {

    private Long requestById;
    private Long requestForId;
    private int[] floorAccess;
    private String kitRequired;
}
