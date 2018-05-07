package at.fhv.roomix.domain.session.configuration;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.Sources;

/**
 * Roomix
 * at.fhv.roomix.domain.session.configuration
 * IUiConfiguration
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
@Sources({"file:~/.app.config",
        "file:/etc/app.config"})
public interface IUiConfiguration extends Config {
    @Key("ui.default.printpath")
    @DefaultValue("C:\\")
    String defaultPrintPath();

    @Key("ui.language")
    @DefaultValue("EN")
    String language();
}
