/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.liaisonmd.client.mvp.view;

import com.liaisonmd.client.mvp.presenter.CompanyPresenter.CompanyViewInterface;
import com.liaisonmd.client.util.Resources;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * Company Presenter's view
 * 
 */
public class CompanyView extends ViewWithUiHandlers<CompanyUiHandlers>
        implements CompanyViewInterface {

    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    private static CompanyViewUiBinder uiBinder = GWT
            .create(CompanyViewUiBinder.class);

    /*
     * @UiField annotaded vars. can be used here from your ui.xml template
     */
    @UiField
    SimplePanel simplePanel;

    private final Widget widget;

    // /////////////////////////////////////////////////////////////////////////
    // Interfaces
    // /////////////////////////////////////////////////////////////////////////

    interface CompanyViewUiBinder extends UiBinder<Widget, CompanyView> {
    }

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    @Inject
    public CompanyView() {
        widget = uiBinder.createAndBindUi(this);

        //HTMLPanel dynContent = new HTMLPanel(Resources.INSTANCE.synchronous().getText());
        //dynContent.add(new Label("This content is dynamically generated."), "day_content");
        
        //simplePanel.add(dynContent); 
    }

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return simplePanel;
    }

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}
