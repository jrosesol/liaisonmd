/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.liaisonmd.shared.proxy;

import com.liaisonmd.server.domain.Domain;
import com.liaisonmd.server.locator.ObjectifyLocator;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * TODO: Add comments for DomainProxy
 *
 */
@ProxyFor(value = Domain.class, locator = ObjectifyLocator.class)
public interface DomainProxy extends BaseProxy {
    public void setName(String domainName);
    public String getName();
}
