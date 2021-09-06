package ro.thales.mytools.registryapp.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.thales.mytools.registryapp.entities.AppUserRole;
import ro.thales.mytools.registryapp.entities.Team;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileResponse implements Serializable {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean hasOfficeIncomeTraining;
    private AppUserRole role;
    private Team team;
    private SimpleUserResponse manager;
}
