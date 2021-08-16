package ro.thales.mytools.registryapp.services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.entities.AppUser;
import ro.thales.mytools.registryapp.entities.AppUserRole;
import ro.thales.mytools.registryapp.entities.RegistrationRequest;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailChecker emailChecker;
    private final AppUserService appUserService;

    public ResponseEntity<AppUser> register(RegistrationRequest request) {
        if (!emailChecker.test(request.getEmail())) {
            throw new IllegalStateException("Email not valid");
        }
        AppUser appUser = appUserService.signUpUser(
                new AppUser(
                        request.getEmail(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getPassword(),
                        AppUserRole.ROLE_USER
                )
        );
        return ResponseEntity.ok(appUser);
    }
}
