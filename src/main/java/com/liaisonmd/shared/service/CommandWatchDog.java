package com.liaisonmd.shared.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.allen_sauer.gwt.log.client.Log;
import com.liaisonmd.client.event.UpdateDataBindedObjectsEvent;
import com.liaisonmd.client.event.UpdateDataBindedObjectsEvent.DATA_EVENT_TYPE;
import com.liaisonmd.shared.proxy.AccountProxy;
import com.liaisonmd.shared.proxy.ActivityProxy;
import com.liaisonmd.shared.proxy.BaseProxy;
import com.liaisonmd.shared.proxy.DomainTimeCodesProxy;
import com.liaisonmd.shared.proxy.TimeEntryProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class CommandWatchDog implements HasHandlers {

    private final EventBus eventBus;

    private final TimesheetRequestFactory rf;

    @Inject
    public CommandWatchDog(final EventBus eventBus, TimesheetRequestFactory rf) {
        this.eventBus = eventBus;
        this.rf = rf;
        rf.initialize(this.eventBus);
    }

    // /////////////////////////////////////////////////////////////////////////

    public void deleteTimeEntry(TimeEntryProxy timeEntry) {
        rf.timeEntryRequest().deleteTimeEntry(timeEntry).fire(getVoidReceiver("deleteTimeEntry"));
    }

    public void listAllTimeEntries(final List<TimeEntryProxy> dstResult) {
        rf.timeEntryRequest().listAll().fire(getListReceiver(dstResult, eventBus, DATA_EVENT_TYPE.LIST_ALL_TIME_ENTRIES));
    }

    public void listAllTimeEntriesInRange(final List<TimeEntryProxy> dstResult, Date startDate, Date endDate) {
        rf.timeEntryRequest().readInRangeTimeEntries(startDate, endDate).fire(getListReceiver(dstResult,
                                                                                              eventBus,
                                                                                              DATA_EVENT_TYPE.LIST_ALL_TIME_ENTRIES));
    }

    // /////////////////////////////////////////////////////////////////////////

    public void listAllAccounts(final List<AccountProxy> dstResult) {
        rf.accountRequest().listAll().fire(getListReceiver(dstResult, eventBus, DATA_EVENT_TYPE.LIST_ALL_ACCOUNTS));
    }

    // /////////////////////////////////////////////////////////////////////////

    public void listAllActivies(final List<ActivityProxy> dstResult) {
        rf.activityListRequest().listAll().fire(getListReceiver(dstResult, eventBus,
                                                                DATA_EVENT_TYPE.LIST_ALL_ACTIVITIES));
    }

    // /////////////////////////////////////////////////////////////////////////

    public void listAllTimeCodes(final List<DomainTimeCodesProxy> dstResult) {
        rf.domainTimeCodeRequest().getTimeCodes().fire(getReceiver(dstResult, eventBus,
                                                                  DATA_EVENT_TYPE.LIST_ALL_TIME_CODES));
    }

    // /////////////////////////////////////////////////////////////////////////

    private static Receiver<Void> getVoidReceiver(final String serviceCallName) {
        Receiver<Void> voidReceiver = new Receiver<Void>() {

            @Override
            public void onSuccess(Void response) {
                System.out.print(serviceCallName);
                Logger.getLogger("service").log(Level.INFO, "RF Call Success: " + serviceCallName);
            }

            @Override
            public void onFailure(ServerFailure error) {
                System.out.print(serviceCallName);
                Logger.getLogger("service").log(Level.WARNING, "RF Call Failed: " + serviceCallName);
            }
        };

        return voidReceiver;
    }

    private static <T> Receiver<T> getReceiver(final List<T> dstResult, final EventBus eventBus,
            final DATA_EVENT_TYPE eventType) {
        Receiver<T> voidReceiver = new Receiver<T>() {

            @Override
            public void onSuccess(T response) {
                Log.info("RF Call Success: " + eventType);

                if (Log.isDebugEnabled()) {
                    BaseProxy obj = (BaseProxy) response;
                    Log.debug("Returned objects: " + obj.getDescription());
                }

                // Copy all elements of the collection
                dstResult.clear();
                dstResult.add(response);

                // Notify the handlers a data event is over successfully
                UpdateDataBindedObjectsEvent.fire(eventBus, eventType);
            }

            @Override
            public void onFailure(ServerFailure error) {
                Log.warn("RF Call Failed: " + eventType.toString());
            }
        };

        return voidReceiver;
    }

    /**
     * @param <T>
     * @param serviceCallName
     * @param type
     * @return
     */
    private static <T> Receiver<List<T>> getListReceiver(final List<T> dstResult, final EventBus eventBus,
            final DATA_EVENT_TYPE eventType) {
        Receiver<List<T>> voidReceiver = new Receiver<List<T>>() {

            @Override
            public void onSuccess(List<T> response) {
                Log.info("RF Call Success: " + eventType);

                if (Log.isDebugEnabled()) {
                    for (T t : response) {
                        BaseProxy obj = (BaseProxy) t;
                        Log.debug("Returned objects: " + obj.getDescription());
                    }
                }

                // Copy all elements of the collection
                dstResult.clear();
                for (T object : response) {
                    dstResult.add(object);
                }

                // Notify the handlers a data event is over successfully
                UpdateDataBindedObjectsEvent.fire(eventBus, eventType);
            }

            @Override
            public void onFailure(ServerFailure error) {
                Log.warn("RF Call Failed: " + eventType.toString());
            }
        };

        return voidReceiver;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

}
