package com.lislon.sat.ws;

import com.lislon.sat.JerseyConfiguration;
import com.lislon.sat.model.Account;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;

public abstract class AbstractIT extends JerseyTest {

    @Override
    protected Application configure() {
        return new JerseyConfiguration();
    }

    protected Account apiGetAccount(int id) {
        return target("accounts/" + id).request().get().readEntity(Account.class);
    }
}
