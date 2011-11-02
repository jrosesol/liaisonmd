package com.liaisonmd.shared.proxy;

import com.liaisonmd.server.domain.AppUser;
import com.liaisonmd.server.locator.ObjectifyLocator;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

@ProxyFor(value = AppUser.class, locator = ObjectifyLocator.class)
public interface AppUserProxy extends BaseProxy {
    public String getEmail();
}
