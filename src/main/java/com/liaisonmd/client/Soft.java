package com.liaisonmd.client;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.liaisonmd.client.gin.GinjectorProvider;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Soft implements EntryPoint {


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
    
    
        final GinjectorProvider ginjector = GWT.create(GinjectorProvider.class);

        // Register all which need delayed binding
        DelayedBindRegistry.bind(ginjector.get());

        // Go to the default place revealDefaultPlace() token
        Log.info("Go to the default place");
        ginjector.get().getPlaceManager().revealDefaultPlace();
        
        // FIXCOMMIT //
        // REMOVE THIS WHEN REAL APP IS DEPLOYED
        //ginjector.getDemoDataLoader().loadDemoData();
    }
}
