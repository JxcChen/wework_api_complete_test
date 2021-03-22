package testcase.member;

import api_ibject.GetToken;
import org.junit.jupiter.api.Test;

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
        System.out.println(GetToken.getToken("wwbcc92e0afe51b09e", "MmNdXbFeCNiPJEztv1Kd1fmorSwiWzUpZkUVLKS72u8"));
    }
}
