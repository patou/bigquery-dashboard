package com.github.bigquery.dashboard.model;

import com.google.gson.annotations.Expose;
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
    private String libelle;

    @Expose
    private String request;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
