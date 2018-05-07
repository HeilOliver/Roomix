package at.fhv.roomix.ui.dataprovider;

import at.fhv.roomix.controller.session.SessionControllerFactory;
import at.fhv.roomix.domain.session.configuration.IUiConfiguration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Roomix
 * at.fhv.roomix.ui.dataprovider
 * ConfigurationProvider
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ConfigurationProvider extends AbstractProvider {

    private ConfigurationProvider() {
    }

    // Blocks until finish
    public static IUiConfiguration getConfiguration() {
        Future<IUiConfiguration> future = submit(() ->
                SessionControllerFactory.getInstance()
                        .getUiConfiguration());

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
    }
}
