package ro.thales.mytools.registryapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.entities.Team;
import ro.thales.mytools.registryapp.responses.TeamListResponse;
import ro.thales.mytools.registryapp.repositories.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<TeamListResponse> getAllTeams(){
        return teamRepository.findAll().stream().map(Team::getTeamResponse).collect(Collectors.toList());
    }
}
