package ro.thales.mytools.registryapp.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class StatusUpdateRequest implements Serializable {
    private Integer status;
}
