package com.liaisonmd.client.mvp.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.liaisonmd.client.mvp.presenter.ErrorPresenter;

public class ErrorView extends ViewImpl implements ErrorPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, ErrorView> {
	}

	@Inject
	public ErrorView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
