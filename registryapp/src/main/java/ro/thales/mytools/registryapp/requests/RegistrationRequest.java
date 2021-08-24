package ro.thales.mytools.registryapp.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor
@Data
public class RegistrationRequest {

    @NotNull(message = "First name cannot be null")
    @Pattern(regexp = "[a-zA-Z ]*", message = "Enter only characters")
    //@NotBlank(message = "First name cannot be whitespaces")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "First name cannot be whitespaces")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Pattern(regexp = "[a-zA-Z ]*", message = "Enter only characters")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Last name cannot be whitespaces")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Please enter a valid email")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Email cannot be whitespaces")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min=6, message = "Password at least 6")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Password cannot be whitespaces")
    private String password;

    @NotNull(message = "Office income training cannot be null")
    private Boolean hasOfficeIncomeTraining;

    @NotNull(message = "Team cannot be null")
    private Long team;

}
