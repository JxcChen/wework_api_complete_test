package Utils;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * ClassName: TimeStampUtil
 * date: 2021/3/29 17:03
 *
 * @author JJJJ
 * Description:
 */
public class FakeDataUtil {
    // 获取时间戳
    public static String getCurrentTimeStamp(){
        return String.valueOf(System.currentTimeMillis());
    }
    // 获取随机手机号
    @Test
    public static String getFakeMobile(){
        Random random = new Random();
        int num = random.nextInt(90000000)+1000000;
        String mobile = "134"+num;
        return mobile;
    }
}
