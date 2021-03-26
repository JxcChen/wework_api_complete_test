package testcase.member;

import api_ibject.GetToken;
import api_ibject.MemberApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
public class MemberTestcase {

    static String token = "";

    @BeforeAll
    public static void getTokenTest(){
        token = GetToken.getToken("wwbcc92e0afe51b09e", "MmNdXbFeCNiPJEztv1Kd1fmorSwiWzUpZkUVLKS72u8");
     }

    @Test
    public void addMemberTest(){
        Response response = MemberApi.addMember("zhangsan99","张三122","+86 13800000121","1",token);
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

    @Test
    public void deleteMemberListTest(){
        ArrayList<String> list = new ArrayList<>();
        list.add("zhangsan99");
        list.add("zhangsan990");
        Response response = MemberApi.deleteMemberList(token, list);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Test
    public void getDepartmentMemberListTest(){
        Response response = MemberApi.getDepartmentUserList(token,"1");
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }


    @Test
    public void getOpenIdTest(){
        Response response = MemberApi.getOpenid(token, "ChenJinXuan");
        System.out.println(response.path("openid").toString());
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }


    @Test
    public void checkInfoTest(){
        Response response = MemberApi.checkInfo(token, "ChenJinXuan");
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Test
    public void inviteMemberTest(){
        ArrayList<String> userIds = new ArrayList<String>();
        userIds.add("ChenJinXuan");
        userIds.add("zhangsan99");
        ArrayList<Integer> partyIds = new ArrayList<Integer>();
        partyIds.add(1);
        Response response = MemberApi.inviteMember(token, userIds, partyIds);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Test
    public void getJoinQrCode(){
        Response response = MemberApi.getJoinQrCode(token);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Test
    public void getActiveMember(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Response response = MemberApi.getActiveStat(token, dateFormat.format(date).toString());
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }
}
