package ro.thales.mytools.registryapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

}
