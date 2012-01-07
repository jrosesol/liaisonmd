/**
 * 
 */

package com.liaisonmd.client.gin;

import com.liaisonmd.client.mvp.presenter.ErrorPresenter;
import com.liaisonmd.client.mvp.presenter.MainPagePresenter;
import com.liaisonmd.client.mvp.presenter.SettingsPresenter;
import com.liaisonmd.client.util.DemoDataLoader;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;

import com.gwtplatform.mvp.client.proxy.PlaceManager;

import com.liaisonmd.client.gin.ClientModule;
import com.google.gwt.inject.client.AsyncProvider;

/**
 * GWT Dependency Injection
 * 
 */
@GinModules({ ClientModule.class })
public interface ClientGinjector extends Ginjector {
	EventBus getEventBus();

	Provider<MainPagePresenter> getMainPagePresenter();

	PlaceManager getPlaceManager();

	DemoDataLoader getDemoDataLoader();

	Provider<ErrorPresenter> getErrorPresenter();

	AsyncProvider<SettingsPresenter> getSettingsPresenter();
}