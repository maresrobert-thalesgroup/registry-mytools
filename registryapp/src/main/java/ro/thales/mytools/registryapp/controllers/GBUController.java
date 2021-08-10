package ro.thales.mytools.registryapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.thales.mytools.registryapp.services.GBUService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/gbu")
@AllArgsConstructor
public class GBUController {
    private final GBUService gbuService;

    @GetMapping
    public ResponseEntity<List<String>> getAllGBUs(){
        return new ResponseEntity<>(gbuService.getAllGBUs(), HttpStatus.OK);
    }
}
