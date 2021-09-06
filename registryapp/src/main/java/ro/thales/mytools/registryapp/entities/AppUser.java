package ro.thales.mytools.registryapp.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="AppUser")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppUser implements UserDetails {
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

    public AppUser(String email, String firstName, String lastName, String password, AppUserRole role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public AppUser(String email, String firstName, String lastName, String password, Boolean hasOfficeIncomeTraining, AppUserRole role, Team team) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.hasOfficeIncomeTraining = hasOfficeIncomeTraining;
        this.role = role;
        this.team = team;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
