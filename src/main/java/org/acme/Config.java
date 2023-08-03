package org.acme;

import io.quarkus.logging.Log;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@Singleton
public class Config {
    
    @ConfigProperty(name="custom.env.config")
    Optional<String> envConfig;
    
    void startup(@Observes StartupEvent event) {
        if (envConfig.isPresent()) {
            Log.info("The config is present, value: " + envConfig.get());
        } else {
            Log.info("The config is empty");
        }

        Quarkus.asyncExit(0);
    }
}