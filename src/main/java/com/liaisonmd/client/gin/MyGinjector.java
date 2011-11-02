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

import com.liaisonmd.client.mvp.presenter.CompanyPresenter;
import com.liaisonmd.client.mvp.presenter.MainPagePresenter;
import com.liaisonmd.client.mvp.presenter.ReportsPresenter;
import com.liaisonmd.client.mvp.presenter.ApprovalPresenter;
import com.liaisonmd.client.mvp.presenter.TimesheetPresenter;
import com.liaisonmd.client.util.DemoDataLoader;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;

import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import com.liaisonmd.client.gin.MyModule;

/**
 * TODO: Add comments for MyGinjector
 * 
 */
@GinModules({ DispatchAsyncModule.class, MyModule.class })
public interface MyGinjector extends Ginjector {
	EventBus getEventBus();

	Provider<MainPagePresenter> getMainPagePresenter();

	PlaceManager getPlaceManager();
	
	DemoDataLoader getDemoDataLoader();
	
	// Asynch providers
	AsyncProvider<TimesheetPresenter> getTimesheetPresenter();
	AsyncProvider<ApprovalPresenter> getTasksPresenter();
	AsyncProvider<ReportsPresenter> getReportsPresenter();
	AsyncProvider<CompanyPresenter> getCompanyPresenter();
}