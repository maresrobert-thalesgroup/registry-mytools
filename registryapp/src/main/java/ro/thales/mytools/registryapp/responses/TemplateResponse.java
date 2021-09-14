package ro.thales.mytools.registryapp.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TemplateResponse implements Serializable {

    private int id;
    private String requestBy;
    private String requestFor;
    private String gbu;
    private String team;
    private int[] floorAccess;
    private String kitRequired;
    private boolean officeIncomeTraining;
}
