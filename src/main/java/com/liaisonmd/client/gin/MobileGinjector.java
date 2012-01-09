/**
 * 
 */
package com.liaisonmd.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * @author Admin
 *
 */
@GinModules({ClientModule.class, MobileGinModule.class})
public interface MobileGinjector extends ClientGinjector {

}
