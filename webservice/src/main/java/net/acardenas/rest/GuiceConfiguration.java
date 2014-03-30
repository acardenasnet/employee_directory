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

package net.acardenas.rest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import net.acardenas.rest.modules.CoreModule;
import net.acardenas.rest.modules.WebserviceModule;

/**
 * Created by acardenas on 3/26/14.
 */
public class GuiceConfiguration
        extends GuiceServletContextListener
{
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new CoreModule(), new WebserviceModule());
    }
}
