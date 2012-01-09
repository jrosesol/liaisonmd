package com.liaisonmd.client.gin;

import com.google.gwt.core.client.GWT;

public class DesktopGinjectorProvider implements GinjectorProvider {
   public ClientGinjector get() { return GWT.create(DesktopGinjector.class); }
}