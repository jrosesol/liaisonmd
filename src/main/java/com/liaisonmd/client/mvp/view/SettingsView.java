package com.liaisonmd.client.mvp.view;

import com.allen_sauer.gwt.log.client.Log;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.liaisonmd.client.mvp.presenter.SettingsPresenter;
import com.liaisonmd.shared.proxy.AccountProxy;
import com.liaisonmd.shared.service.TimesheetRequestFactory;
import com.liaisonmd.shared.service.TimesheetRequestFactory.AccountRequestContext;

public class SettingsView extends ViewImpl implements SettingsPresenter.MyView, Editor<AccountProxy> {

	private final Widget widget;
	private Driver driver = GWT.create(Driver.class);
	
	private TimesheetRequestFactory requestFactory;
	
	@UiField protected TextBox name;
	@UiField protected Button saveButton;

	public interface Binder extends UiBinder<Widget, SettingsView> {
	}
	
	public interface Driver extends RequestFactoryEditorDriver<AccountProxy, SettingsView> {
		
	}

	@Inject
	public SettingsView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	protected AccountProxy newAccountProxy;

	/* (non-Javadoc)
	 * @see com.liaisonmd.client.mvp.presenter.SettingsPresenter.MyView#initializeDriver(com.liaisonmd.shared.service.TimesheetRequestFactory)
	 */
	@Override
	public void initializeDriver(TimesheetRequestFactory requestFactory) {
		driver.initialize(requestFactory, this);
		
		this.requestFactory = requestFactory;
		
		//
		AccountRequestContext account = requestFactory.accountRequest();
		newAccountProxy = account.create(AccountProxy.class);
		
		// Test value
		newAccountProxy.setName("System & Settings project");
		
		// Copy the data in the object into the UI
		driver.edit(newAccountProxy, account);
		
		// Add a handler to the save button
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AccountRequestContext account = (AccountRequestContext) driver.flush();
				
				account.saveAccountAndReturn(newAccountProxy).fire(new Receiver<AccountProxy>() {

					@Override
					public void onFailure(ServerFailure error) {
						Log.error("Could not save the Account");
					}

					@Override
					public void onSuccess(AccountProxy response) {
						Log.info("Save OK");
						refreshEditor(response);
					}					
				});
			}			
		});
	}
	
	public void refreshEditor(AccountProxy accountProxy) {

		//
		AccountRequestContext account = requestFactory.accountRequest();
		newAccountProxy = accountProxy;
		//newAccountProxy = account.create(AccountProxy.class);
		//newAccountProxy.setName(accountProxy.getName());

		// Copy the data in the object into the UI
		driver.edit(newAccountProxy, account);
	}
}
