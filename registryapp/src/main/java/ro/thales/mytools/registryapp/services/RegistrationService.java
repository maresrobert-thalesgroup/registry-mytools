package ro.thales.mytools.registryapp.services;

import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.entities.RegistrationRequest;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "works";
    }
}
