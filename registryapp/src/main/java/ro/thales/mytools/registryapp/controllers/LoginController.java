package ro.thales.mytools.registryapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @GetMapping
    public ResponseEntity<String> onLoginSuccessful(){
        return new ResponseEntity<>("auth success", HttpStatus.OK);
    }
}
