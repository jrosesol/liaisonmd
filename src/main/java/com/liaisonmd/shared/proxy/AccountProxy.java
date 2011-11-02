package com.liaisonmd.shared.proxy;

import com.liaisonmd.server.domain.Account;
import com.liaisonmd.server.locator.ObjectifyLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Account.class, locator = ObjectifyLocator.class)
public interface AccountProxy extends BaseProxy {
    public void setName(String name);
    public String getName();
    public Long getId();
}
