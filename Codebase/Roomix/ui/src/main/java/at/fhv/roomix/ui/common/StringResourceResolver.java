package at.fhv.roomix.ui.common;

import at.fhv.roomix.ui.dataprovider.AbstractProvider;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.common
 * StringResourceResolver
 * 17/04/2018 Oliver
 * <p>
 * This class is for conversion between an resource key and the underlying
 * value. If no resource with the given key is found or an value is not
 * an {@code String} no exception will be thrown. The key will be used as
 * value and an Log entry will be made.
 */
public class StringResourceResolver {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractProvider.class);
    private StringProperty resultString = new SimpleStringProperty();

    public StringResourceResolver(ResourceBundle bundle, ReadOnlyStringProperty stringProperty) {
        stringProperty.addListener((observable, oldValue, newValue) -> {
            String string = newValue;
            try {
                string = bundle.getString(newValue);
            } catch (MissingResourceException | ClassCastException ignore) {
                LOG.debug("Cant find Resource - " + newValue);
            }
            resultString.setValue(string);
        });

    }

    public ReadOnlyStringProperty getResultProperty() {
        return resultString;
    }

    public static ReadOnlyStringProperty getAnonymousProperty(
            ResourceBundle bundle, ReadOnlyStringProperty stringProperty) {
        StringResourceResolver resolver = new StringResourceResolver(bundle, stringProperty);
        return resolver.getResultProperty();
    }
}
