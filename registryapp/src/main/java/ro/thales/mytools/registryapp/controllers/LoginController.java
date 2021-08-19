package ro.thales.mytools.registryapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class LoginController {
    @GetMapping(path = "/greeting")
    public ResponseEntity<String> onLoginSuccessful(){
        return new ResponseEntity<>("auth success", HttpStatus.OK);
    }
}
