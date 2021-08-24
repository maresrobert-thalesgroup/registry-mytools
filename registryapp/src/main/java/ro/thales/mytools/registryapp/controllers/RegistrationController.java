package ro.thales.mytools.registryapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ro.thales.mytools.registryapp.requests.RegistrationRequest;
import ro.thales.mytools.registryapp.responses.RegistrationResponse;
import ro.thales.mytools.registryapp.services.RegistrationService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
@CrossOrigin()
public class RegistrationController {

    private RegistrationService registrationService;

    /*

    @InitBinder("registrationRequest")
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        if (binder.getTarget() != null
                && RegistrationRequest.class.equals(binder.getTarget().getClass())) {
            StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
            binder.registerCustomEditor(String.class,stringTrimmerEditor);
        }
    }

     */

    @PostMapping
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(registrationService.register(request));
    }
}
