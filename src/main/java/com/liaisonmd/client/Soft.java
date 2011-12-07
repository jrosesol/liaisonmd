package com.liaisonmd.client;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.liaisonmd.client.gin.ClientGinjector;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Soft implements EntryPoint {

    public final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        // Register all which need delayed binding
        DelayedBindRegistry.bind(ginjector);

        // Go to the default place revealDefaultPlace() token
        Log.info("Go to the default place");
        ginjector.getPlaceManager().revealDefaultPlace();
        
        // FIXCOMMIT //
        // REMOVE THIS WHEN REAL APP IS DEPLOYED
        ginjector.getDemoDataLoader().loadDemoData();
    }
}
