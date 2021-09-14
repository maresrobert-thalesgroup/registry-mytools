package ro.thales.mytools.registryapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.thales.mytools.registryapp.ResourceNotFoundException;
import ro.thales.mytools.registryapp.entities.Template;
import ro.thales.mytools.registryapp.repositories.TemplateRepository;
import ro.thales.mytools.registryapp.requests.TemplateRequest;
import ro.thales.mytools.registryapp.responses.TemplateResponse;
import ro.thales.mytools.registryapp.services.TemplateService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1")
public class TemplateController {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateService templateService;

    @PostMapping("/templates")
    public Template addTemplate(@RequestBody TemplateRequest templateRequest) {
        return templateService.addTemplate(templateRequest);
    }


    @GetMapping("/templates")
    public ResponseEntity<List<TemplateResponse>> getAllTemplates() {

        return ResponseEntity.ok(templateService.getAllTemplates());
    }

    @GetMapping("/templates/list/{id}")
    public ResponseEntity<List<TemplateResponse>> getAllTemplatesByUserId(@PathVariable(value = "id")  Integer userId) {

        return ResponseEntity.ok(templateService.getAllTemplatesByUserId(userId));
    }

    @GetMapping("/templates/{id}")
    public ResponseEntity<Template> getTemplateById(@PathVariable(value = "id") Integer templateId)
            throws ResourceNotFoundException {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found for this id :: " + templateId));
        return ResponseEntity.ok().body(template);
    }

    @PutMapping("/templates/{id}")
    public ResponseEntity<Template> updateTemplate(@PathVariable(value = "id") Integer templateId,
                                                   @RequestBody TemplateRequest templateRequest) throws ResourceNotFoundException {

        Template updatedTemplate = templateService.updateTemplate(templateId,templateRequest);
            return ResponseEntity.ok(updatedTemplate);
    }

    @DeleteMapping("/templates/{id}")
    public Map<String, Boolean> deleteTemplate(@PathVariable(value = "id") Integer templateId)
            throws ResourceNotFoundException {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found for this id :: " + templateId));

        templateRepository.delete(template);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
