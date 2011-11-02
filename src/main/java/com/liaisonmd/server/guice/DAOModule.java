package com.liaisonmd.server.guice;

import com.allen_sauer.gwt.log.client.Log;
import com.liaisonmd.server.domain.Account;
import com.liaisonmd.server.domain.Activity;
import com.liaisonmd.server.domain.AppUser;
import com.liaisonmd.server.domain.Domain;
import com.liaisonmd.server.domain.DomainTimeCodes;
import com.liaisonmd.server.domain.TimeEntry;
import com.google.inject.AbstractModule;
import com.googlecode.objectify.ObjectifyService;

public class DAOModule extends AbstractModule {

    @Override
    protected void configure() {
        ObjectifyService.register(AppUser.class);
        ObjectifyService.register(Activity.class);
        ObjectifyService.register(Account.class);
        ObjectifyService.register(TimeEntry.class);
        ObjectifyService.register(DomainTimeCodes.class);
        ObjectifyService.register(Domain.class);
                
        Log.setCurrentLogLevel(Log.LOG_LEVEL_INFO);
    }
}
