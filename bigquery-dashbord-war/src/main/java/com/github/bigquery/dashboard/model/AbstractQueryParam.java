package com.github.bigquery.dashboard.model;

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
    private String name;
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

    static public enum Type {
        TEXT, DATE, LIST, NUMBER
    }

    @Subclass
    public static class TextQueryParam extends AbstractQueryParam {
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
        private List<String> values; // for Type LIST
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
