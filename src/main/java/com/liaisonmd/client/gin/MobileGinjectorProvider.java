package com.liaisonmd.client.gin;

import com.google.gwt.core.client.GWT;

public class MobileGinjectorProvider implements GinjectorProvider {
   public ClientGinjector get() { return GWT.create(MobileGinjector.class); }
}