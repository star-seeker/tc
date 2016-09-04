package com.ysu.zyw.tc.components.cache;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.base.tools.IdWorker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @warn every business part must have it's own cache impl(or impl a cache key generator),
 * and this place only for test cacheable annotation, it will clear the codis(default) cache.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:config/tc-spring-cache.xml",
        "classpath*:config/tc-spring-import-cache.xml"
})
public class CacheableTest {

    @Resource
    private MongoPool mongoPool;

    @Before
    public void setUp() throws Exception {
        // mongoPool.clearCache();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test() {
        String uuid1 = IdWorker.upperCaseUuid();
        Mongo mongo1 = new Mongo(uuid1,
                Lists.newArrayList(new Mongo.Orange(IdWorker.upperCaseUuid())));
        Mongo mongo2 = new Mongo(uuid1,
                Lists.newArrayList(new Mongo.Orange(IdWorker.upperCaseUuid())));

        assertMethodCall(0, 0, 0, 0, 0);

        // selectOne 1
        Assert.assertNull(mongoPool.select(uuid1));
        // select 1 selectOne 1(from cache)
        Assert.assertEquals(0, mongoPool.select(Lists.newArrayList(uuid1)).size());
        assertMethodCall(0, 0, 0, 1, 1);

        // insert 1
        mongoPool.insert(mongo1);

        // selectOne 3
        Assert.assertNotNull(mongoPool.select(uuid1));
        // select 2 selectOne 1(from cache)
        Assert.assertEquals(1, mongoPool.select(Lists.newArrayList(uuid1)).size());
        assertMethodCall(1, 0, 0, 2, 1);

        // select 3 selectOne 1(from cache)
        Assert.assertEquals(1, mongoPool.select(Lists.newArrayList(uuid1)).size());
        assertMethodCall(1, 0, 0, 3, 1);

        // update 1
        mongoPool.update(mongo2);
        // selectOne 1(from cache)
        Assert.assertNotNull(mongoPool.select(uuid1));

        assertMethodCall(1, 0, 1, 3, 1);

        // delete 1(clear cache)
        mongoPool.delete(uuid1);

        // select 4 selectOne 2
        Assert.assertEquals(0, mongoPool.select(Lists.newArrayList(uuid1)).size());
        assertMethodCall(1, 1, 1, 4, 2);

        // clear cache
        mongoPool.clearCache();

        // selectOne 3
        Assert.assertNull(mongoPool.select(uuid1));

        assertMethodCall(1, 1, 1, 4, 3);
    }

    private void assertMethodCall(int insertCall,
                                  int deleteCall,
                                  int updateCall,
                                  int selectCall,
                                  int selectOneCall) {
        if (insertCall != -1) {
            Assert.assertEquals(insertCall, MongoPool.insertCall);
        }
        if (deleteCall != -1) {
            Assert.assertEquals(deleteCall, MongoPool.deleteCall);
        }
        if (updateCall != -1) {
            Assert.assertEquals(updateCall, MongoPool.updateCall);
        }
        if (selectCall != -1) {
            Assert.assertEquals(selectCall, MongoPool.selectCall);
        }
        if (selectOneCall != -1) {
            Assert.assertEquals(selectOneCall, MongoPool.selectOneCall);
        }
    }

}