package ro.thales.mytools.registryapp.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.entities.AppUser;
import ro.thales.mytools.registryapp.entities.GBU;
import ro.thales.mytools.registryapp.repositories.AppUserRepository;
import ro.thales.mytools.registryapp.responses.SimpleUserResponse;
import ro.thales.mytools.registryapp.responses.UserProfileResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("email " + email + "  not found"));
    }

    public UserProfileResponse getUserByEmail(String email) {
        AppUser appUser = appUserRepository.findByEmail(email).get();
        SimpleUserResponse simpleUserResponse;
        if(appUserRepository.findManagerByTeam(appUser.getTeam().getId()).isPresent()){
            AppUser appUserManager = appUserRepository.findManagerByTeam(appUser.getTeam().getId()).get();
            simpleUserResponse = new SimpleUserResponse(appUserManager.getId(),appUserManager.getEmail());
        } else {
            simpleUserResponse = new SimpleUserResponse();
        }
        UserProfileResponse userProfileResponse = new UserProfileResponse(appUser.getId(), appUser.getEmail(), appUser.getFirstName(), appUser.getLastName(), appUser.getHasOfficeIncomeTraining(), appUser.getRole(), appUser.getTeam(), simpleUserResponse);
        return userProfileResponse;
    }

    public AppUser signUpUser(AppUser appUser) {
        if (appUserRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new IllegalStateException("Email in use");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        return appUserRepository.save(appUser);
    }

    public List<SimpleUserResponse> getAllUsersInTeam(Long team_id){
        return this.appUserRepository.getAllUsersInTeam(team_id).get().stream().map(AppUser::getSimpleResponse).collect(Collectors.toList());
    }
}
