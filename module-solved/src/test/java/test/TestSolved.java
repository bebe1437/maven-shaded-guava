package test;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestSolved {
    private static final Logger LOG = LoggerFactory.getLogger(TestSolved.class);
    private static HBaseTestingUtility UTILITY = new HBaseTestingUtility();

    @BeforeClass
    public static void beforeClass(){
        UTILITY.getConfiguration().set("hbase.zookeeper.property.clientPort", "2181");
        try {
            UTILITY.startMiniCluster();
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertNull(e);
        }

    }

    @AfterClass
    public static void afterClass(){
        try{
            UTILITY.shutdownMiniCluster();
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertNull(e);
        }
    }

    @Test
    public void test(){
        LOG.info("started");
        String tmp ="test";
        int hashCode = TestGuava.hasCode(tmp);
        LOG.info("hashCode:{}", hashCode);
        int encodedLength = TestGuava.encodedLength(tmp);
        LOG.info("encodedLength:{}", encodedLength);
    }
}
