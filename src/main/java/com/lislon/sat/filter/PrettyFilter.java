package com.lislon.sat.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.cfg.EndpointConfigBase;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.cfg.ObjectWriterInjector;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.cfg.ObjectWriterModifier;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class PrettyFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext reqCtx, ContainerResponseContext respCtx) {
        ObjectWriterInjector.set(new IndentingModifier());
    }

    public static class IndentingModifier extends ObjectWriterModifier {
        @Override
        public ObjectWriter modify(EndpointConfigBase<?> endpointConfigBase,
                                   MultivaluedMap<String, Object> multivaluedMap,
                                   Object o,
                                   ObjectWriter objectWriter,
                                   JsonGenerator jsonGenerator) {
            jsonGenerator.useDefaultPrettyPrinter();
            return objectWriter;
        }
    }
}