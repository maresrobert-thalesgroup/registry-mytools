package ro.thales.mytools.registryapp.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GBUListResponse implements Serializable {
    private Long id;
    private String name;
}
