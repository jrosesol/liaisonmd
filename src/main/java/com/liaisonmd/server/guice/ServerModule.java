/**
 *
 */

package com.liaisonmd.server.guice;

import com.liaisonmd.server.service.ObjectifyDao;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

/**
 * Module which binds the handlers and configurations.
 * 
 * @author Jose Rose
 */
public class ServerModule extends AbstractModule {
   
    @Override
    protected void configure() {
        bind(RequestFactoryServlet.class).in(Singleton.class);
    }
        
}
