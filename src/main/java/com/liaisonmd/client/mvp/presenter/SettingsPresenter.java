package com.liaisonmd.client.mvp.presenter;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.liaisonmd.client.place.NameTokens;
import com.liaisonmd.shared.service.TimesheetRequestFactory;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class SettingsPresenter extends
		Presenter<SettingsPresenter.MyView, SettingsPresenter.MyProxy> {
	
	private final TimesheetRequestFactory requestFactory;

	public interface MyView extends View {
		/**
		 * Used to initialise the Editor driver and start editing the object.
		 * @param requestFactory The request factory. Use code injection.
		 */
		public void initializeDriver(final TimesheetRequestFactory requestFactory);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.settings)
	public interface MyProxy extends ProxyPlace<SettingsPresenter> {
	}

	@Inject
	public SettingsPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final TimesheetRequestFactory requestFactory) {
		super(eventBus, view, proxy);
		
		this.requestFactory = requestFactory;
		
		getView().initializeDriver(this.requestFactory);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
}
