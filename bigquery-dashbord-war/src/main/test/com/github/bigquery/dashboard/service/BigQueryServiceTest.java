package com.github.bigquery.dashboard.service;

import static org.fest.assertions.Assertions.*;

import com.github.bigquery.dashboard.model.AbstractQueryParam;
import com.github.bigquery.dashboard.model.BigQuery;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cache.AsyncCacheFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class BigQueryServiceTest {
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
            new LocalDatastoreServiceTestConfig().setApplyAllHighRepJobPolicy(),
            new LocalMemcacheServiceTestConfig(),
            new LocalTaskQueueTestConfig());
    private Closeable closable;

    @BeforeClass
    public static void setUpBeforeClass()
    {

        //ObjectifyService.reset();
        // Reset the Factory so that all translators work properly.
        ObjectifyService.setFactory(new ObjectifyFactory() {
            @Override
            public Objectify begin()
            {
                return super.begin().cache(false);
            }
        });

    }

    @Before
    public void setUp() {
        helper.setUp();
        closable = ObjectifyService.begin();
        OfyService.factory();
    }

    @After
    public void tearDown() {
        helper.tearDown();
        AsyncCacheFilter.complete();
        try {
            closable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testList() throws Exception {

    }

    @Test
    public void testListAll() throws Exception {

    }

    @Test
    public void testList1() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {
        BigQuery query = new BigQuery();
        query.setLabel("test");
        query.setRequest("SELECT * FROM test");
        BigQueryService.createOrUpdate(query);

        BigQueryService.delete(1L);

        BigQuery bigQuery = BigQueryService.get(1L);
        assertThat(bigQuery).isNull();
    }

    @Test
    public void testGet() throws Exception {
        BigQuery query = new BigQuery();
        query.setLabel("test");
        query.setRequest("SELECT * FROM test");
        BigQueryService.createOrUpdate(query);

        BigQuery bigQuery = BigQueryService.get(1L);
        assertThat(bigQuery).isNotNull().isEqualTo(query);
    }

    @Test
    public void testGetWithParams() throws Exception {
        BigQuery query = new BigQuery();
        query.setLabel("test");
        query.setRequest("SELECT * FROM test");
        BigQueryService.createOrUpdate(query);

        BigQuery bigQuery = BigQueryService.get(1L);
        assertThat(bigQuery).isNotNull().isEqualTo(query);
    }

    @Test
    public void testCreateOrUpdateSimple() throws Exception {
        BigQuery query = new BigQuery();
        query.setLabel("test");
        query.setRequest("SELECT * FROM @toto");
        AbstractQueryParam.TextQueryParam param = new AbstractQueryParam.TextQueryParam();
        param.setName("toto");
        param.setDefaultValue("test");
        AbstractQueryParam.DateQueryParam param2 = new AbstractQueryParam.DateQueryParam();
        param2.setName("toto2");
        param2.setDefaultValue(new Date());
        query.setParams(Arrays.asList(param, param2));
        BigQueryService.createOrUpdate(query);

        BigQuery bigQuery = BigQueryService.get(1L);
        assertThat(bigQuery).isNotNull().isEqualTo(query);
        assertThat(query.getParams()).hasSize(2);
    }

    @Test
    public void testCreateOrUpdateWithParams() throws Exception {
        BigQuery query = new BigQuery();
        query.setLabel("test");
        query.setRequest("SELECT * FROM @toto");
        AbstractQueryParam.TextQueryParam param = new AbstractQueryParam.TextQueryParam();
        param.setName("toto");
        param.setDefaultValue("test");
        AbstractQueryParam.DateQueryParam param2 = new AbstractQueryParam.DateQueryParam();
        param2.setName("toto2");
        param2.setDefaultValue(new Date());
        AbstractQueryParam.ListQueryParam param3 = new AbstractQueryParam.ListQueryParam();
        param3.setName("toto3");
        param3.setValues(Arrays.asList("test", "test1", "test2"));
        param3.setDefaultValue("test");
        AbstractQueryParam.NumberQueryParam param4 = new AbstractQueryParam.NumberQueryParam();
        param4.setName("toto4");
        param4.setDefaultValue(1);

        query.setParams(Arrays.asList(param, param2, param3, param4));
        BigQueryService.createOrUpdate(query);
    }
}