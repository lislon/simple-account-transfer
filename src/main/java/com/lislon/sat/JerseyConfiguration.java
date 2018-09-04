package com.lislon.sat;

import com.lislon.sat.filter.PrettyFilter;
import com.lislon.sat.mockdata.PredefinedAccounts;
import com.lislon.sat.service.AccountsService;
import com.lislon.sat.service.TransactionsService;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import java.util.logging.Level;

/**
 * Main application configuration.
 */
@ApplicationPath("/api")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        packages("com.lislon.sat");
        // HK2 dependency injection setting up
        register(new SimpleAccountTransferBinder());

        // json prettifier
        register(PrettyFilter.class);

        // more verbose loggin
        register(new LoggingFeature(null, Level.ALL, LoggingFeature.Verbosity.PAYLOAD_ANY, 1000));

        // generates /api/openapi.json file
        register(OpenApiResource.class);
    }

    public static class SimpleAccountTransferBinder extends AbstractBinder {
        @Override
        protected void configure() {
            bindFactory(PrepopulatedAccountsServiceFactory.class, Singleton.class)
                    .to(AccountsService.class)
                    .in(Singleton.class);

            bind(TransactionsService.class)
                    .to(TransactionsService.class)
                    .in(Singleton.class);
        }

        public static class PrepopulatedAccountsServiceFactory implements Factory<AccountsService> {
            @Override
            public AccountsService provide() {
                AccountsService accountsService = new AccountsService();
                accountsService.populateAccounts(PredefinedAccounts.getPredefinedAccounts());
                return accountsService;
            }

            @Override
            public void dispose(AccountsService instance) {
            }
        }
    }
}
