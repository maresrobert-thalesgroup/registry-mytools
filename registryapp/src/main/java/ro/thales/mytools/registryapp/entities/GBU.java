package ro.thales.mytools.registryapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name="GBUs")
@Data
@Builder
public class GBU {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(
            mappedBy = "gbu",
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private List<Team> teamList = new ArrayList<>();

    // incercare de comm
}
