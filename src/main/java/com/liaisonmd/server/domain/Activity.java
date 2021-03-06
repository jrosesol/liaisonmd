package com.liaisonmd.server.domain;

import com.liaisonmd.server.service.AppUserDao;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Activity extends DatastoreObject {
    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////

    private String name;
    
    // Keys
    private Key<AppUser> owningUser;
    private Key<Account> owningAccount;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    
    public Activity() {
        
    }

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////
    
    @Override
    public String toString() {
        return "[name : " + getName() + ", id: " + getId() + ", owningUser : " + getOwner() + ", owningAccount : " + owningAccount + "]";
    }

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////
    
    public String getDescription() {
        return this.toString();
    }
    
    public void setOwner(AppUser owningUser) {
        this.owningUser = new AppUserDao().key(owningUser);
    }

    public AppUser getOwner() {
        try {
            return new AppUserDao().get(owningUser);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOwningAccount(Account owningAccount) {
        this.owningAccount = new Key<Account>(Account.class, owningAccount.getId());
    }

    public Key<Account> getOwningAccount() {
        return owningAccount;
    }
}
