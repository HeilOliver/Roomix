package at.fhv.roomix.webLogin.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("frontoffice")
public class MethodFrontOfficeRestController {
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('FRONTOFFICE')")
    public ResponseEntity<?> getProtectedGreeting() {
        return ResponseEntity.ok("Greetings from Frontoffice protected method!");
    }
}