package com.github.bigquery.dashboard.model;

import com.google.gson.annotations.Expose;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Subclass;

import java.util.Date;
import java.util.List;

/**
 * Created by b002kla on 18/12/2014.
 */
@Entity
public abstract class AbstractQueryParam {
    @Parent
    private Key<BigQuery> bigQuery;
    @Id
    @Expose
    private String name;
    @Expose
    private String label;
    @Expose
    private Type type;

    public Key<BigQuery> getBigQuery() {
        return bigQuery;
    }

    public void setBigQuery(Key<BigQuery> bigQuery) {
        this.bigQuery = bigQuery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    static public enum Type {
        TEXT, DATE, LIST, NUMBER
    }

    @Subclass
    public static class TextQueryParam extends AbstractQueryParam {
        @Expose
        private String defaultValue;

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }
    }
    @Subclass
    public static class DateQueryParam extends AbstractQueryParam {
        @Expose
        private Date defaultValue;

        public Date getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(Date defaultValue) {
            this.defaultValue = defaultValue;
        }
    }
    @Subclass
    public static class NumberQueryParam extends AbstractQueryParam {
        @Expose
        private Number defaultValue;

        public Number getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(Number defaultValue) {
            this.defaultValue = defaultValue;
        }
    }
    @Subclass
    public static class ListQueryParam extends AbstractQueryParam {
        @Expose
        private List<String> values; // for Type LIST
        @Expose
        private String defaultValue;

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }
    }
}
