package ro.thales.mytools.registryapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.thales.mytools.registryapp.requests.EmailRequest;
import ro.thales.mytools.registryapp.responses.UserProfileResponse;
import ro.thales.mytools.registryapp.services.AppUserService;

@RestController
@RequestMapping(path = "/api/v1/profile")
@AllArgsConstructor
@CrossOrigin()
public class ProfileController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<UserProfileResponse> register(@RequestBody EmailRequest emailRequest) {
        return ResponseEntity.ok(appUserService.getUserByEmail(emailRequest.getEmail()));
    }
}
