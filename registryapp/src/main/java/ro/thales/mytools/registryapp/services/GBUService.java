package ro.thales.mytools.registryapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.entities.GBU;
import ro.thales.mytools.registryapp.responses.GBUListResponse;
import ro.thales.mytools.registryapp.repositories.GBURepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GBUService {
    private final GBURepository gbuRepository;

    public List<GBUListResponse> getAllGBUs(){
        //return gbuRepository.findAll().stream().map(GBU::getName).collect(Collectors.toList());
        List<GBUListResponse> gbuListResponseList = new ArrayList<>();
        for(GBU g: gbuRepository.findAll()){
            gbuListResponseList.add(new GBUListResponse(g.getId(),g.getName()));
        }
        return gbuListResponseList;
    }
}
