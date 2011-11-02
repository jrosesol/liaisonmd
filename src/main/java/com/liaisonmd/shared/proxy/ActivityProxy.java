package com.liaisonmd.shared.proxy;

import com.liaisonmd.server.domain.Activity;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.liaisonmd.server.locator.ObjectifyLocator;

@ProxyFor(value = Activity.class, locator = ObjectifyLocator.class)
public interface ActivityProxy extends BaseProxy {
    public String getName();
    public void setName(String name);
    public Long getId();
}
