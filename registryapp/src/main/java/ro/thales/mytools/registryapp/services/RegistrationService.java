package ro.thales.mytools.registryapp.services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.entities.*;
import ro.thales.mytools.registryapp.repositories.TeamRepository;
import ro.thales.mytools.registryapp.requests.RegistrationRequest;
import ro.thales.mytools.registryapp.responses.RegistrationResponse;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailChecker emailChecker;
    private final AppUserService appUserService;
    private final TeamRepository teamRepository;

    public ResponseEntity<RegistrationResponse> register(RegistrationRequest request) {
        if (!emailChecker.test(request.getEmail())) {
            throw new IllegalStateException("Email not valid");
        }
        AppUser appUser = appUserService.signUpUser(
                new AppUser(
                        request.getEmail(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getPassword(),
                        request.getHasOfficeIncomeTraining(),
                        AppUserRole.ROLE_USER,
                        teamRepository.findById(request.getTeam()).get()
                )
        );
        return ResponseEntity.ok(new RegistrationResponse(appUser.getId(),appUser.getFirstName(),appUser.getLastName(),appUser.getEmail(),appUser.getHasOfficeIncomeTraining(),appUser.getRole(),appUser.getTeam().getId()));
    }
}
