/**
 * 
 */
package com.liaisonmd.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.liaisonmd.client.mvp.presenter.MainPagePresenter;
import com.liaisonmd.client.mvp.view.MainPageMobileView;

/**
 * @author Admin
 *
 */
public class MobileGinModule extends AbstractPresenterModule {

	/* (non-Javadoc)
	 * @see com.google.gwt.inject.client.AbstractGinModule#configure()
	 */
	@Override
	protected void configure() {
		bindPresenter(MainPagePresenter.class,
				MainPagePresenter.MainPageViewInterface.class,
                MainPageMobileView.class, MainPagePresenter.MainPageProxy.class);
	}

}
