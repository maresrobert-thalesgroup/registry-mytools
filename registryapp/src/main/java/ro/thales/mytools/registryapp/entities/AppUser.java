package ro.thales.mytools.registryapp.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="AppUser")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean hasOfficeIncomeTraining;
    @Enumerated(EnumType.STRING)
    private AppUserRole role;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
