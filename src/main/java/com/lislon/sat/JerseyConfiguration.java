package com.lislon.sat;

import com.lislon.sat.filter.PrettyFilter;
import com.lislon.sat.mockdata.PredefinedAccounts;
import com.lislon.sat.service.AccountsService;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Singleton;

/**
 * Created by ele on 30.08.2018.
 */
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        packages("com.lislon.sat");
        register(new MyApplicationBinder());
        register(PrettyFilter.class);
    }


    public class MyApplicationBinder extends AbstractBinder {
        @Override
        protected void configure() {
            bindFactory(MyClass.class, Singleton.class).to(AccountsService.class).in(Singleton.class);
        }
    }
    static class MyClass implements Factory<AccountsService> {
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

    interface SimpleFactory<T> extends Factory<T> {
        default void dispose(T instance) {
        }
    }
}