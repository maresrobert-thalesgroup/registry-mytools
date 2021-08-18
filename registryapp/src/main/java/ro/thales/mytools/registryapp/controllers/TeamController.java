package ro.thales.mytools.registryapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.thales.mytools.registryapp.responses.TeamListResponse;
import ro.thales.mytools.registryapp.services.TeamService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/team")
@AllArgsConstructor
@CrossOrigin()
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamListResponse>> getAllTeams(){
        return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
    }
}
