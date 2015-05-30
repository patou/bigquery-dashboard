package com.github.bigquery.dashboard.model;

import com.github.bigquery.dashboard.service.OfyService;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 01/10/2014.
 */
@Cache
@Entity
public class BigQuery {
    @Id
    private Long id;

    @Index
    @AlsoLoad("libelle")
    private String label;

    @Index
    private String comment;

    private String request;
    private String icons;

    @Load(value = WithParams.class)
    @JsonIgnore
    List<Ref<AbstractQueryParam>> refParams = new ArrayList<>();

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

    public void setKeyParams(Collection<Key<AbstractQueryParam>> keyParams) {
        refParams.clear();
        for (Key<AbstractQueryParam> keyParam : keyParams) {
            refParams.add(Ref.create(keyParam));
        }
    }

    public List<AbstractQueryParam> getParams() {
        if (params == null && refParams.size() > 0) {
            params = new ArrayList<>();
            if (refParams.get(0).isLoaded()) {
                for (Ref<AbstractQueryParam> refParam : refParams) {
                    params.add(refParam.get());
                }
            }
        }
        return params;
    }

    public void setParams(List<AbstractQueryParam> params) {
        this.params = params;
    }

    public static class WithParams {}
}
