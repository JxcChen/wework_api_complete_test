package testcase.department;

import Utils.FakeDataUtil;
import api_object.DepartmentApi;
import api_object.GetToken;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author: JJJJ
 * @date:2021/4/2 18:30
 * @Description: TODO
 */
public class DepartmentTestcase {
    static String accessToken;
    static String testPartyId;
    @BeforeAll
    public static void init(){
        // secret : MmNdXbFeCNiPJEztv1Kd1fmorSwiWzUpZkUVLKS72u8
        // copid : wwbcc92e0afe51b09e
        String copid = "wwbcc92e0afe51b09e";
        String secret = "MmNdXbFeCNiPJEztv1Kd1fmorSwiWzUpZkUVLKS72u8";
        accessToken = GetToken.getToken(copid, secret);
    }


    /**
     * 为不同用例创建测试数据  除增加部门用例
     */
    @BeforeEach
    public void beforeEach(){
        String partyName = "测试部门"+FakeDataUtil.getCurrentTimeStamp();
        String parentId = "1";
        Response response = DepartmentApi.addParty(accessToken, partyName, parentId);
        testPartyId = response.path("id").toString();
    }


    @Test
    void addPartyTest(){
        String partyName = "新建的部门"+FakeDataUtil.getCurrentTimeStamp();
        String parentId = "1";
        String enName = "newParty"+FakeDataUtil.getCurrentTimeStamp();
        String order = "100";
        String partyId = "11";
        Response response = DepartmentApi.addParty(accessToken, partyName, enName, parentId, order, partyId);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Test
    void addPartyTest2(){
        String partyName = "新建的部门2"+FakeDataUtil.getCurrentTimeStamp();
        String parentId = "1";
        Response response = DepartmentApi.addParty(accessToken, partyName, parentId);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Test
    void updatePartyTest(){
        String newPartyName = "修改部门名称"+FakeDataUtil.getCurrentTimeStamp();
        Response response = DepartmentApi.updateParty(accessToken, testPartyId, newPartyName);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Test
    void deletePartyTest(){
        Response response = DepartmentApi.deleteParty(accessToken, testPartyId);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Test
    void getPartyListTest(){
        Response response = DepartmentApi.getPartyList(accessToken, "1");
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }
}
