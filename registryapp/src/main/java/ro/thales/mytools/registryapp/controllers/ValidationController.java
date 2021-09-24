package ro.thales.mytools.registryapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ro.thales.mytools.registryapp.repositories.AppUserRepository;
import ro.thales.mytools.registryapp.requests.ValidationRequest;
import ro.thales.mytools.registryapp.security.JwtTokenUtil;

@RestController
@CrossOrigin()
@RequestMapping(path = "/validate")
public class ValidationController {
    private AppUserRepository appUserRepository;

    @PostMapping
    public ResponseEntity<Boolean> validateToken(@RequestBody ValidationRequest validationRequest){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        if(!username.equals(validationRequest.getEmail())) return ResponseEntity.ok(false);
        else if(!validationRequest.getRole().equals(userDetails.getAuthorities().toArray()[0].toString())) return ResponseEntity.ok(false);
        else return ResponseEntity.ok(true);
    }
}
