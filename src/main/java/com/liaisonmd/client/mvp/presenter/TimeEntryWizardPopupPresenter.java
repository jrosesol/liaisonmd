/**
 *
 *
 * @author Jose Rose
 * 2011-04-29
 */
package com.liaisonmd.client.mvp.presenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.allen_sauer.gwt.log.client.Log;
import com.liaisonmd.client.controller.DataStoreProxy;
import com.liaisonmd.client.event.CreateTimeEntryEvent;
import com.liaisonmd.client.event.UpdateDataBindedObjectsEvent;
import com.liaisonmd.client.event.UpdateDataBindedObjectsEvent.DATA_EVENT_TYPE;
import com.liaisonmd.client.event.UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler;
import com.liaisonmd.client.ui.Portlet;
import com.liaisonmd.shared.proxy.DomainTimeCodesProxy;
import com.liaisonmd.shared.proxy.TimeEntryProxy;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HasHandlers;

/**
 * ProjectPopupDetails Presenter implementation
 * Handles the actions possible on the site
 */
public class TimeEntryWizardPopupPresenter extends
    PresenterWidget<TimeEntryWizardPopupPresenter.TimeEntryWizardPopupViewInterface> {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    private final EventBus eventBus;
    private final DataStoreProxy dataStoreProxy;

    // TODO : Find a better way to do this... 
    private long eventSourceUID;
    
    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    /**
     * {@link TimeEntryWizardPopupPresenter}'s view.
     * Here it extends HasUiHandlers to be able to call setUiHandlers.
     */
    public interface TimeEntryWizardPopupViewInterface extends PopupView {
        public HasClickHandlers onWizardOkButton();
        public TimeEntryProxy getTimeEntryProxy();
        public void setTimeEntryCodes(Map<Long, String> domainTimeCodes);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public TimeEntryWizardPopupPresenter(EventBus eventBus, TimeEntryWizardPopupViewInterface view, DataStoreProxy dataStoreProxy) {
        super(eventBus, view);
        
        this.eventBus = eventBus;
        this.dataStoreProxy = dataStoreProxy;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * This is the handler when the user clicks the OK button for the create time entry dialog.
     * @return The handler
     */
    private ClickHandler onWizardOkButton() {
        return new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                CreateTimeEntryEvent.fire(eventBus, getEventSourceUID(), getView().getTimeEntryProxy());
            }
        };
    }
    
    /**
     * Fill the time code values to the time entry wizard.
     * 
     * @return The handler
     */
    private UpdateDataBindedObjectsHandler onRevealPresenters() {
        return new UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler() {
            
            @Override
            public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent updateDataBindedObjectsEvent,
                    DATA_EVENT_TYPE eventType) {
                if (eventType == DATA_EVENT_TYPE.REVEAL_PRESENTERS) {
                    Map<Long, String> timeCodes = dataStoreProxy.getDomainTimeCodeMap();                    
                    getView().setTimeEntryCodes(timeCodes);
                }
            }
        };
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
        
    @Override
    protected void onBind() {
        super.onBind();
        
        registerHandler(getView().onWizardOkButton().addClickHandler(onWizardOkButton()));
        
        registerHandler(eventBus.addHandler(UpdateDataBindedObjectsEvent.getType(), onRevealPresenters()));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Getters / Setters
    ///////////////////////////////////////////////////////////////////////////
    
    public void setEventSourceUID(long eventSourceUID) {
        this.eventSourceUID = eventSourceUID;
    }

    public long getEventSourceUID() {
        return eventSourceUID;
    }

}
