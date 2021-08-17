package ro.thales.mytools.registryapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamListResponse implements Serializable {
    private Long id;
    private String name;
    private Long gbu_id;
}
