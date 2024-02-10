package org.acme.model;

import java.util.ArrayList;
import java.util.List;

public class ListTransaction {
    private String userName;
    private List<Transaction> resources = new ArrayList<>() ;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public List<Transaction> getResources() {
        return resources;
    }
    public void addResource(Transaction transaction) {
        this.resources.add(transaction);
    }
}
