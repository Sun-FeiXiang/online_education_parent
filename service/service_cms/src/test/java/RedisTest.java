import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author Feixiang Sun
 * @date 2021/11/10 11:24
 */
public class RedisTest {

    @Test
    public void test() {
        Jedis jedis = new Jedis("119.91.104.184", 6379);
        System.out.println("TestPing >>>>>>>>>>>>> " + jedis.ping());
    }
}
