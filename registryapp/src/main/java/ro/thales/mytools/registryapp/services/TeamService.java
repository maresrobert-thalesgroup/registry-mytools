package ro.thales.mytools.registryapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.entities.Team;
import ro.thales.mytools.registryapp.repositories.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<String> getAllTeams(){
        return teamRepository.findAll().stream().map(Team::getName).collect(Collectors.toList());
    }
}
