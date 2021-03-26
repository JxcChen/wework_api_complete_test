package testcase.member;

import api_ibject.GetToken;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: TokenTest
 * date: 2021/3/22 16:27
 *
 * @author JJJJ
 * Description:
 */
public class TokenTest {


    @Test
    public void getTokenTest(){
//        System.out.println(GetToken.getToken("wwbcc92e0afe51b09e", "MmNdXbFeCNiPJEztv1Kd1fmorSwiWzUpZkUVLKS72u8"));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date).toString());
    }
}
