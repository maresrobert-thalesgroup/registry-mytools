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

    public RegistrationResponse register(RegistrationRequest request) {
        /*
        if (!emailChecker.test(request.getEmail())) {
            throw new IllegalStateException("Email not valid");
        }
         */

        if (!teamRepository.findById(request.getTeam()).isPresent()) {
            throw new IllegalStateException("Team id does not exist");
        }


        AppUser appUser = appUserService.signUpUser(
                //ADD BUILDER INSTEAD
//                new AppUser(
//                        request.getEmail(),
//                        request.getFirstName(),
//                        request.getLastName(),
//                        request.getPassword(),
//                        request.getHasOfficeIncomeTraining(),
//                        AppUserRole.ROLE_USER,
//                        teamRepository.findById(request.getTeam()).get()
//                )
                AppUser.builder()
                        .email(request.getEmail())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .password(request.getPassword())
                        .hasOfficeIncomeTraining(request.getHasOfficeIncomeTraining())
                        .role(AppUserRole.ROLE_USER)
                        .team(teamRepository.findById(request.getTeam()).get())
                        .build()
        );
        return new RegistrationResponse(appUser.getId(),appUser.getFirstName(),appUser.getLastName(),appUser.getEmail(),appUser.getHasOfficeIncomeTraining(),appUser.getRole(),appUser.getTeam().getId());
    }
}
