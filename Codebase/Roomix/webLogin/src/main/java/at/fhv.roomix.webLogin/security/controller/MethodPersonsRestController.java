package at.fhv.roomix.webLogin.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("persons")
public class MethodPersonsRestController {
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getProtectedGreeting() {
        return ResponseEntity.ok("Greetings from User protected method!");
    }
}
