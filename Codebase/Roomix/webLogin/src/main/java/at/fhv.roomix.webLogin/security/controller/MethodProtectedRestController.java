package at.fhv.roomix.webLogin.security.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("protected")
public class MethodProtectedRestController {

    /**
     * This is an example of some different kinds of granular restriction for endpoints. You can use the built-in SPEL expressions
     * in @PreAuthorize such as 'hasRole()' to determine if a user has access. Remember that the hasRole expression assumes a
     * 'ROLE_' prefix on all role names. So 'ADMIN' here is actually stored as 'ROLE_ADMIN' in database!
     **/
    //Todo: Redirection to other pages!
/*    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProtectedGreeting() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin.html");
        return new ResponseEntity<String>(headers,HttpStatus.FOUND);
        //return ResponseEntity.ok("Greetings from admin protected method!");
    }*/

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView getProtectedGreeting() {
        return new RedirectView("/admin.html");
        //return ResponseEntity.ok("Greetings from admin protected method!");
    }

}