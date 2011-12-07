/**
 * 
 */

package com.liaisonmd.client.place;

import com.liaisonmd.client.mvp.presenter.MainPagePresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class ClientPlaceManager extends PlaceManagerImpl {

	private final PlaceRequest defaultPlaceRequest;
	private final PlaceRequest errorPlaceRequest;

	@Inject
	public ClientPlaceManager(EventBus eventBus,
			final TokenFormatter tokenFormatter,
			@DefaultPlace final String defaultPlaceNameToken,
			@ErrorPlace final String errorPlaceNameToken) {
		super(eventBus, tokenFormatter);
		
		this.defaultPlaceRequest = new PlaceRequest(defaultPlaceNameToken);
		this.errorPlaceRequest = new PlaceRequest(errorPlaceNameToken);
	}

	@Override
	public void revealDefaultPlace() {
		//revealPlace(new PlaceRequest(NameTokens.getMain()));
		revealPlace(defaultPlaceRequest, false);
	}

	@Override
	public void revealErrorPlace(String invalidHistoryToken) {
		revealPlace(errorPlaceRequest, false);
	}
}
