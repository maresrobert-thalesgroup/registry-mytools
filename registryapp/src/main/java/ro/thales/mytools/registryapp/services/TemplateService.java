package ro.thales.mytools.registryapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.ResourceNotFoundException;
import ro.thales.mytools.registryapp.entities.AppUser;
import ro.thales.mytools.registryapp.entities.AppUserRole;
import ro.thales.mytools.registryapp.entities.Team;
import ro.thales.mytools.registryapp.entities.Template;
import ro.thales.mytools.registryapp.repositories.AppUserRepository;
import ro.thales.mytools.registryapp.repositories.TeamRepository;
import ro.thales.mytools.registryapp.repositories.TemplateRepository;
import ro.thales.mytools.registryapp.requests.TemplateRequest;
import ro.thales.mytools.registryapp.responses.TeamListResponse;
import ro.thales.mytools.registryapp.responses.TemplateResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final AppUserRepository appUserRepository;

    public Template addTemplate(TemplateRequest templateRequest) {

        Template template = Template.builder()
                .requestBy(appUserRepository.findById(templateRequest.getRequestById()).get())
                .requestFor(appUserRepository.findById(templateRequest.getRequestForId()).get())
                .floorAccess(templateRequest.getFloorAccess())
                .kitRequired(templateRequest.getKitRequired())
                .build();

        List<Template> templateList = templateRepository.findAll();

        template.checkForSimilar(templateList);

        templateRepository.save(template);

        return template;

    }

    public List<TemplateResponse> getAllTemplates() {

        List<Template> templateList = templateRepository.findAll();

        List<TemplateResponse> templateResponseList = new ArrayList<TemplateResponse>();


        for (int i = 0; i < templateList.size(); i++) {
            TemplateResponse templateResponse = TemplateResponse.builder()
                    .id(templateList.get(i).getId())
                    .requestBy(templateList.get(i).getRequestBy().getEmail())
                    .requestFor(templateList.get(i).getRequestFor().getEmail())
                    .gbu(templateList.get(i).getRequestBy().getTeam().getGbu().getName())
                    .team(templateList.get(i).getRequestBy().getTeam().getName())
                    .floorAccess(templateList.get(i).getFloorAccess())
                    .kitRequired(templateList.get(i).getKitRequired())
                    .officeIncomeTraining(templateList.get(i).getRequestBy().getHasOfficeIncomeTraining())
                    .build();

            templateResponseList.add(templateResponse);

        }

        return templateResponseList;

    }

    public Template updateTemplate(Integer templateId, TemplateRequest templateRequest) {

        Template template = templateRepository.findById(templateId).
                orElseThrow(() -> new ResourceNotFoundException("Template not found for this id :: " + templateId));

        template.setRequestBy(appUserRepository.findById(templateRequest.getRequestById()).get());
        template.setRequestFor(appUserRepository.findById(templateRequest.getRequestForId()).get());
        template.setFloorAccess(templateRequest.getFloorAccess());
        template.setKitRequired(templateRequest.getKitRequired());

        template.checkForSimilar(templateRepository.findAll());

        templateRepository.save(template);

        return template;

    }

    public List<TemplateResponse> getAllTemplatesByUserId(Integer userId) {

        List<Template> templateList = templateRepository.findAllTemplatesByUserId(userId);

        List<TemplateResponse> templateResponseList = new ArrayList<TemplateResponse>();


        for (int i = 0; i < templateList.size(); i++) {
            TemplateResponse templateResponse = TemplateResponse.builder()
                    .id(templateList.get(i).getId())
                    .requestBy(templateList.get(i).getRequestBy().getEmail())
                    .requestFor(templateList.get(i).getRequestFor().getEmail())
                    .gbu(templateList.get(i).getRequestBy().getTeam().getGbu().getName())
                    .team(templateList.get(i).getRequestBy().getTeam().getName())
                    .floorAccess(templateList.get(i).getFloorAccess())
                    .kitRequired(templateList.get(i).getKitRequired())
                    .officeIncomeTraining(templateList.get(i).getRequestBy().getHasOfficeIncomeTraining())
                    .build();

            templateResponseList.add(templateResponse);

        }

        return templateResponseList;

    }

}

