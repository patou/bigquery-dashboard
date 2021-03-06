package com.github.bigquery.dashboard.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Subclass;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.Date;
import java.util.List;

/**
 * Created by b002kla on 18/12/2014.
 */
@Entity
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=AbstractQueryParam.TextQueryParam.class, name="TEXT"),
        @JsonSubTypes.Type(value=AbstractQueryParam.DateQueryParam.class, name="DATE"),
        @JsonSubTypes.Type(value=AbstractQueryParam.ListQueryParam.class, name="LIST"),
        @JsonSubTypes.Type(value=AbstractQueryParam.NumberQueryParam.class, name="NUMBER"),
})
public abstract class AbstractQueryParam {
    @Parent
    @JsonIgnore
    private Key<BigQuery> bigQuery;
    @Id
    protected String name;
    protected String label;
    protected Type type;

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
    @JsonTypeName("TEXT")
    public static class TextQueryParam extends AbstractQueryParam {
        private String defaultValue;

        public TextQueryParam() {
            type = Type.TEXT;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }
    }
    @Subclass
    @JsonTypeName("DATE")
    public static class DateQueryParam extends AbstractQueryParam {
        private Date defaultValue;

        public DateQueryParam() {
            type = Type.DATE;
        }

        public Date getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(Date defaultValue) {
            this.defaultValue = defaultValue;
        }
    }
    @Subclass
    @JsonTypeName("NUMBER")
    public static class NumberQueryParam extends AbstractQueryParam {
        private Double defaultValue;

        public NumberQueryParam() {
            type = Type.NUMBER;
        }

        public Number getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(Double defaultValue) {
            this.defaultValue = defaultValue;
        }
    }
    @Subclass
    @JsonTypeName("LIST")
    public static class ListQueryParam extends AbstractQueryParam {
        private List<String> values; // for Type LIST
        private String defaultValue;

        public ListQueryParam() {
            type = Type.LIST;
        }

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
