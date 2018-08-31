package com.lislon.sat;

import com.lislon.sat.mockdata.PredefinedAccounts;
import com.lislon.sat.service.AccountsService;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by ele on 30.08.2018.
 */
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        packages("com.lislon.sat");
        register(new MyApplicationBinder());
    }

    public class MyApplicationBinder extends AbstractBinder {
        @Override
        protected void configure() {
            bindSimpleFactory(
                    () -> {
                        AccountsService accountsService = new AccountsService();
                        accountsService.populateAccounts(PredefinedAccounts.getPredefinedAccounts());
                        return accountsService;
                    }).to(AccountsService.class);
        }

        <T> ServiceBindingBuilder<T> bindSimpleFactory(SimpleFactory<T> factory) {
            return bindFactory(factory);
        }
    }

    private interface SimpleFactory<T> extends Factory<T> {
        default void dispose(T instance) {
        }
    }
}