package test;

import com.github.dockerjava.api.command.CreateContainerCmd;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.*;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.util.function.Consumer;


public class TestContainer {

    @ClassRule
    public static FixedHostPortGenericContainer CONTAINER;

    private static final Integer[] PORTS = {2181, 60000, 60010, 60020, 60030};

    static {
        CONTAINER = new FixedHostPortGenericContainer("test-hbase");
        for (int port : PORTS) {
            CONTAINER.withFixedExposedPort(port, port);
        }
        CONTAINER
            .waitingFor(Wait
                            .forHttp("/")
                            .forPort(60010))
            .withCreateContainerCmdModifier(new Consumer<CreateContainerCmd>() {
                @Override
                public void accept(CreateContainerCmd createContainerCmd) {
                    createContainerCmd.withHostName("qmi_docker");
                }
            })
            .withCommand("tail -F /dev/null");

    }

    @BeforeClass
    public static void beforeClass() {
        try {
            CONTAINER.start();
            boolean isRunning = CONTAINER.isRunning();
            Assert.assertTrue(isRunning);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertNull(e);
        }
    }

    @AfterClass
    public static void afterClass() {
        try {
            CONTAINER.stop();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertNull(e);
        }
    }

    @Test
    public void test(){
        try {
            Connection connection = ConnectionFactory.createConnection();
            Admin admin = connection.getAdmin();
            byte[] family = "cf".getBytes();
            byte[] table = "test".getBytes();
            HTableDescriptor descriptor = new HTableDescriptor(table);
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(family);
            descriptor.addFamily(columnDescriptor);
            admin.createTable(descriptor);
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertNull(e);
        }

    }
}
