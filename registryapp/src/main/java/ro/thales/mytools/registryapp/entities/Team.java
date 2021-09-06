package ro.thales.mytools.registryapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import ro.thales.mytools.registryapp.responses.TeamListResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Teams")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "gbu_id")
    private GBU gbu;
    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    @JsonIgnore
    private List<AppUser> members = new ArrayList<>();



    public TeamListResponse getTeamResponse() {
        return new TeamListResponse(this.getId(), this.getName(), this.getGbu().getId());
    }
}
