package com.github.bigquery.dashboard.model;

import com.googlecode.objectify.annotation.*;

/**
 * 01/10/2014.
 */
@Cache
@Entity
public class Dashboard {
    @Id
    private Long id;

    @Index
    private String label;

    @Index
    private String user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
