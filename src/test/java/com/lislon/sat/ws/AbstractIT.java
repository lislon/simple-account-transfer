package com.lislon.sat.ws;

import com.lislon.sat.JerseyConfiguration;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;

public abstract class AbstractIT extends JerseyTest {

    @Override
    protected Application configure() {
        return new JerseyConfiguration();
    }

}
