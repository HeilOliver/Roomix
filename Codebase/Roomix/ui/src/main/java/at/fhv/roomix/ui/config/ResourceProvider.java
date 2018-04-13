package at.fhv.roomix.ui.config;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.config
 * ResourceProvider
 * 25/03/2018 OliverH
 * <p>
 * A singleton Contexts and Dependency Injection provider
 * that is used to load the resource bundle and provide
 * it for the Contexts and Dependency injection.
 */
@Singleton
public class ResourceProvider {

    /*
     * Due to the @Produces annotation this resource bundle can be injected in all views.
     */
    @Produces
    private ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("default");

}