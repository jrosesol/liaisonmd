/**
 *
 *
 * @author Jose Rose
 * 2011-08-09
 */
package com.liaisonmd.server.service;

import com.liaisonmd.server.domain.Domain;
import com.liaisonmd.shared.TooManyResultsException;

/**
 * TODO: Add comments for DomainDao
 *
 */
public class DomainDao extends ObjectifyDao<Domain> {
    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    
    public Domain registerDomain(Domain newDomain) {
        // Check the domain is not registered, otherwise return the
        // matching domain
        Domain registeredDomain = null;
        try {
            registeredDomain = getByProperty(Domain.NAME_FIELD_NAME, newDomain.getName());
        } catch (TooManyResultsException e1) {
            e1.printStackTrace();
        }
        
        if (registeredDomain == null) {
            // Now save the domain
            this.put(newDomain);
            try {
                return newDomain;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return registeredDomain;
        }        
    }
}
