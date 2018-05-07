package at.fhv.roomix.domain.session.configuration;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.Sources;

/**
 * Roomix
 * at.fhv.roomix.domain.session.configuration
 * Configuration
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
@Sources({"file:~/.myapp.config",
        "file:/etc/myapp.config"})
public interface Configuration extends Config {

    @Key("server.database.port")
    int port();

    @Key("server.database.username")
    String username();

    @Key("server.database.password")
    String password();

    @Key("server.database.address")
    String address();
}
