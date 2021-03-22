package testcase.member;

import api_ibject.GetToken;
import api_ibject.MemberApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ClassName: MemberTestcase
 * date: 2021/3/22 16:44
 *
 * @author JJJJ
 * Description:
 */
public class MemberTestcase {

    static String token = "";


    @BeforeAll
    public static void getTokenTest(){
        token = GetToken.getToken("wwbcc92e0afe51b09e", "MmNdXbFeCNiPJEztv1Kd1fmorSwiWzUpZkUVLKS72u8");
     }

    @Test
    public void addMemberTest(){
        Response response = MemberApi.addMember("zhangsan99","张三12","+86 13800000120","1",token);
        assertThat(response.path("errcode").toString(),containsString("0"));
    }

    @Test
    public void getMemberMesgTest(){
        Response response = MemberApi.getMemberMessage(token,"zhangsan99");
        assertThat(response.path("errcode").toString(),containsString("0"));
    }


    @Test
    public void updateMembeTest(){
        Response response = MemberApi.updateMember(token,"zhangsan99","小三","1");
        assertThat(response.path("errcode").toString(),containsString("0"));
    }

}
