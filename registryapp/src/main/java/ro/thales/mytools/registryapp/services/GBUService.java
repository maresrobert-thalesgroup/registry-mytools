package ro.thales.mytools.registryapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.entities.GBU;
import ro.thales.mytools.registryapp.repositories.GBURepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GBUService {
    private final GBURepository gbuRepository;

    public List<String> getAllGBUs(){
        return gbuRepository.findAll().stream().map(GBU::getName).collect(Collectors.toList());
    }
}
