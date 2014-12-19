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

    @Load(value = WithParams.class)
    List<Ref<AbstractQueryParam>> refParams = new ArrayList<>();

    @Expose
    @Ignore
    List<AbstractQueryParam> params = null;

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

    public List<Ref<AbstractQueryParam>> getRefParams() {
        return refParams;
    }

    public void setRefParams(List<Ref<AbstractQueryParam>> refParams) {
        this.refParams = refParams;
    }

    public List<AbstractQueryParam> getParams() {
        if (params == null && refParams.size() > 0) {
            params = new ArrayList<>();
            for (Ref<AbstractQueryParam> ref : refParams) {
                if (ref.isLoaded()) {
                    params.add(ref.get());
                }
            }
        }
        return params;
    }

    public void setParams(List<AbstractQueryParam> params) {
        this.params = params;
        if (params != null) {
            refParams.clear();
            for (AbstractQueryParam ref : params) {
                refParams.add(Ref.create(ref));
            }
        }
    }

    public static class WithParams {}
}
