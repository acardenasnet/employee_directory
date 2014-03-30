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

package net.acardenas.rest.modules;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import net.acardenas.rest.EmployeeService;
import net.acardenas.rest.jackson.CustomJacksonJaxbJsonProvider;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by acardenas on 3/26/14.
 */
public class WebserviceModule
    extends ServletModule
{
    @Override
    protected void configureServlets() {
        bindRestResources();

        bind(JacksonJaxbJsonProvider.class).to(CustomJacksonJaxbJsonProvider.class).asEagerSingleton();

        Map<String, String> params = new HashMap<String, String>();
        params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        serve("/*").with(GuiceContainer.class, params);
    }

    /**
     * bind the REST resources
     */
    protected void bindRestResources() {
        bind(EmployeeService.class);
    }
}
