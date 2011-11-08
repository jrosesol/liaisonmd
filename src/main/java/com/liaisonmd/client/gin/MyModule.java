/**
 * Copyright 2010 ArcBees Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.liaisonmd.client.gin;

import java.util.logging.Logger;

import com.liaisonmd.client.controller.DataStoreProxy;
import com.liaisonmd.client.i18n.AppsConstants;
import com.liaisonmd.client.mvp.presenter.MainPagePresenter;
import com.liaisonmd.client.mvp.presenter.MyRootPresenter;
import com.liaisonmd.client.mvp.view.MainPageView;
import com.liaisonmd.client.place.MyPlaceManager;
import com.liaisonmd.client.util.DemoDataLoader;
import com.liaisonmd.server.domain.TimeEntryCodes;
import com.liaisonmd.shared.service.CommandWatchDog;
import com.liaisonmd.shared.service.TimesheetRequestFactory;
import com.liaisonmd.shared.service.TimesheetRequestFactory.TimeEntryRequestContext;
import com.liaisonmd.shared.service.TimesheetRequestFactory.AccountRequestContext;
import com.liaisonmd.shared.service.TimesheetRequestFactory.ActivityListRequestContext;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import com.liaisonmd.client.place.DefaultPlace;
import com.liaisonmd.client.place.NameTokens;

/**
 * @author Jose Rose
 */
public class MyModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceManager.class).to(MyPlaceManager.class).in(Singleton.class);
        bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
        bind(RootPresenter.class).to(MyRootPresenter.class).asEagerSingleton();

        // User bindings
        bind(DataStoreProxy.class).asEagerSingleton();
        bind(CommandWatchDog.class).asEagerSingleton();
        //bind(TimesheetRequestFactory.class).asEagerSingleton();
        bind(DemoDataLoader.class).asEagerSingleton();

        // Presenters
        bindPresenter(MainPagePresenter.class, MainPagePresenter.MainPageViewInterface.class, MainPageView.class,
                      MainPagePresenter.MainPageProxy.class);

    }

    @Provides
    @Singleton
    public TimesheetRequestFactory createTimesheetRequestFactory( EventBus eventBus ) {
        TimesheetRequestFactory factory = GWT.create( TimesheetRequestFactory.class );
        factory.initialize( eventBus );
        return factory;
    }

    @Provides
    public TimeEntryRequestContext createTimeEntryRequestContext( TimesheetRequestFactory factory ) {
        return factory.timeEntryRequest();
    }

    @Provides
    public ActivityListRequestContext createActivityListRequestContext( TimesheetRequestFactory factory ) {
        return factory.activityListRequest();
    }

    @Provides
    public AccountRequestContext createAccountRequestContext( TimesheetRequestFactory factory ) {
        return factory.accountRequest();
    }
    
    @Provides
    @Singleton
    public AppsConstants createAppConstants() {
        AppsConstants lConstants = (AppsConstants) GWT.create(AppsConstants.class);
        return lConstants;
    }   
}