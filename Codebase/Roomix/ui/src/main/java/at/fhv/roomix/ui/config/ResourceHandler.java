package at.fhv.roomix.ui.config;

import at.fhv.roomix.ui.views.main.models.PageProvider;
import org.apache.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.config
 * ResourceHandler
 * 06/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ResourceHandler {

    private ResourceHandler() {
    }

    public static String getLocalizedString(String resourceName) {
        final ResourceBundle bundle = ResourceBundle.getBundle("default");
        try {
            return bundle.getString(resourceName);
        } catch (MissingResourceException e) {
            Logger logger = Logger.getLogger(PageProvider.class);
            logger.error("Missing Resource - " + resourceName, e.getCause());
            return resourceName;
        }
    }
}
