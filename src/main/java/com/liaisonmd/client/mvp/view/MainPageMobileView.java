package com.liaisonmd.client.mvp.view;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.liaisonmd.client.mvp.presenter.MainPagePresenter;

public class MainPageMobileView extends ViewWithUiHandlers<MainPageUiHandlers>
	implements MainPagePresenter.MainPageViewInterface {

	private final Widget widget;
	
    private static MainPageViewUiBinder uiBinder = GWT
            .create(MainPageViewUiBinder.class);

	public interface MainPageViewUiBinder extends UiBinder<Widget, MainPageMobileView> {
		Widget createAndBindUi(MainPageMobileView mainPageView);
	}

	@Inject
	public MainPageMobileView() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
