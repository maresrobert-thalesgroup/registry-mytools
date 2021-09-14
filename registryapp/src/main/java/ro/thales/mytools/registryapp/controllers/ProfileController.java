package ro.thales.mytools.registryapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.thales.mytools.registryapp.requests.EmailRequest;
import ro.thales.mytools.registryapp.responses.SimpleUserResponse;
import ro.thales.mytools.registryapp.responses.UserProfileResponse;
import ro.thales.mytools.registryapp.services.AppUserService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/profile")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProfileController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<UserProfileResponse> register(@RequestBody EmailRequest emailRequest) {
        return ResponseEntity.ok(appUserService.getUserByEmail(emailRequest.getEmail()));
    }

    @GetMapping(path = "/{team_id}")
    public ResponseEntity<List<SimpleUserResponse>> getAllUsersInTeam(@PathVariable Long team_id){
        return ResponseEntity.ok(appUserService.getAllUsersInTeam(team_id));
    }

    @GetMapping(path = "/users")
        public ResponseEntity<List<UserProfileResponse>> getAllUsers(){
            return ResponseEntity.ok(appUserService.getAllUsers());
        }

}
