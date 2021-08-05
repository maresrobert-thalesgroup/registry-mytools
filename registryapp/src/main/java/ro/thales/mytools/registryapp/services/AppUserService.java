package ro.thales.mytools.registryapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.entities.AppUser;
import ro.thales.mytools.registryapp.repositories.AppUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    public void addAppUser(AppUser appUser){
        Optional<AppUser> appUserEmail = appUserRepository.findAppUserByEmail(appUser.getEmail());
        if(appUserEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        appUserRepository.save(appUser);
    }
}
