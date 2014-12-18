package com.github.bigquery.dashboard.model;

import com.google.gson.annotations.Expose;
import com.googlecode.objectify.annotation.AlsoLoad;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * 01/10/2014.
 */
@Cache
@Entity
public class BigQuery {
    @Id
    @Expose
    private Long id;

    @Expose
    @Index
    @AlsoLoad("libelle")
    private String label;

    @Expose
    @Index
    private String comment;

    @Expose
    private String request;
    @Expose
    private String icons;

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

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }
}
