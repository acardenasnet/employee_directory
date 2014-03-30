/*
 * ---------------------------------------------------------------------------
 *  COPYRIGHT Alejandro Cardenas, acardenas.net, Saltillo,Coah, MX %year.
 *  All rights reserved.
 *
 *  The Copyright to the computer program(s) herein is the property of
 *  Alejandro Raul Cardenas
 *  The program(s) may be used and/or copied only with the written
 *  permission from Alejandro Cardenas, or in
 *  accordance with the terms and conditions stipulated in the
 *  agreement/contract under which the program(s) have been supplied.
 *  ---------------------------------------------------------------------------
 */

package net.acardenas.rest.jackson;

import org.codehaus.jackson.jaxrs.Annotations;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import javax.ws.rs.core.MediaType;

/**
 * Created by acardenas on 3/26/14.
 */
public class CustomJacksonJaxbJsonProvider
        extends JacksonJaxbJsonProvider
{
    public CustomJacksonJaxbJsonProvider()
    {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public CustomJacksonJaxbJsonProvider(Annotations... annotationsToUse)
    {
        super(annotationsToUse);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public CustomJacksonJaxbJsonProvider(ObjectMapper mapper, Annotations[] annotationsToUse)
    {
        super(mapper, annotationsToUse);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public ObjectMapper locateMapper(Class<?> type, MediaType mediaType)
    {
        ObjectMapper mapper = super.locateMapper(type, mediaType);
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}
