package testcase.member;

import api_ibject.GetToken;
import api_ibject.MemberApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * ClassName: MemberTestcase
 * date: 2021/3/22 16:44
 *
 * @author JJJJ
 * Description:
 *  现在每个用例的参数都是写死的  不能单个用例独立执行 没有前置删除测试数据方法
 */
public class MemberTestcase02 {

    static String token = "";

    @BeforeAll
    public static void init(){
        // 先获取道token
        token = GetToken.getToken("wwbcc92e0afe51b09e", "MmNdXbFeCNiPJEztv1Kd1fmorSwiWzUpZkUVLKS72u8");
        // 清空测试数据
     }

    @Test
    public void addMemberTest(){
        Response response = MemberApi.addMember("zhangsan99","张三12","+86 13800000120","1",token);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Test
    public void getMemberMesgTest(){
        Response response = MemberApi.getMemberMessage(token,"zhangsan99");
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }


    @Test
    public void updateMemberTest(){
        Response response = MemberApi.updateMember(token,"zhangsan99","小三","1");
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }


    @Test
    public void deleteMemberTest(){
        Response response = MemberApi.deleteMember(token,"zhangsan99");
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

}
