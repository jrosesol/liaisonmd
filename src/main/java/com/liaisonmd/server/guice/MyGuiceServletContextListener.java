package com.liaisonmd.server.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.liaisonmd.server.guice.DispatchServletModule;

public class MyGuiceServletContextListener extends GuiceServletContextListener {


  @Override
  protected Injector getInjector() {
    return Guice
        .createInjector(new ServerModule(), new DispatchServletModule(), new DAOModule());
  }
}