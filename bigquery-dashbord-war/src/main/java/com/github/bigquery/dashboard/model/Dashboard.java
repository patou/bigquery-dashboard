package com.github.bigquery.dashboard.model;

import com.google.gson.annotations.Expose;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 01/10/2014.
 */
@Cache
@Entity
public class Dashboard {
    @Id
    @Expose
    private Long id;

    @Expose
    @Index
    private String label;

    @Expose
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
