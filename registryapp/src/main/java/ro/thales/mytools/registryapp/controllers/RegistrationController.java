package ro.thales.mytools.registryapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.thales.mytools.registryapp.entities.AppUser;
import ro.thales.mytools.registryapp.entities.RegistrationRequest;
import ro.thales.mytools.registryapp.services.RegistrationService;

@RestController
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
@CrossOrigin()
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<AppUser> register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping("/test")
    public String getString(){
        return "working";
    }
}
