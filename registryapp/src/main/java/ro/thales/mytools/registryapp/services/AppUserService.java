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
import ro.thales.mytools.registryapp.repositories.AppUserRepository;
import ro.thales.mytools.registryapp.responses.UserProfileResponse;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("email " + email + "  not found"));
    }

    public UserProfileResponse getUserByEmail(String email){
        AppUser appUser = appUserRepository.findByEmail(email).get();
        UserProfileResponse userProfileResponse = new UserProfileResponse(appUser.getId(),appUser.getEmail(),appUser.getFirstName(),appUser.getLastName(),appUser.getHasOfficeIncomeTraining(),appUser.getRole(),appUser.getTeam());
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
}
