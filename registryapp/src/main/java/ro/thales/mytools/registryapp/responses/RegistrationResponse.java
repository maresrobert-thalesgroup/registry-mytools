package ro.thales.mytools.registryapp.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.thales.mytools.registryapp.entities.AppUserRole;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean hasOfficeIncomeTraining;
    private AppUserRole appUserRole;
    private Long teamId;
}
