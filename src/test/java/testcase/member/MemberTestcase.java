package testcase.member;

import Utils.FakeDataUtil;
import api_object.GetToken;
import api_object.MemberApi;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * ClassName: MemberTestcase
 * date: 2021/3/22 16:44
 *
 * @author JJJJ
 * Description:
 *  现在每个用例的参数都是写死的  不能单个用例独立执行 没有前置删除测试数据方法
 */

@Epic("企业微信接口测试用例")
@Feature("成员管理用例")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberTestcase {

    static String token = "";
    static String userId = "";
    @BeforeAll
    public static void init(){
        // 1、获取token
        token = GetToken.getToken("wwbcc92e0afe51b09e", "MmNdXbFeCNiPJEztv1Kd1fmorSwiWzUpZkUVLKS72u8");
        // 2、批量删除测试数据
        // 2.1 先获取到测试成员ID列表
        ArrayList<String> userIdList = MemberApi.getDepartmentUserIdList(token, "2");
        // 2.3 进行批量删除
        MemberApi.deleteMemberList(token, userIdList);
    }

    @BeforeEach
    public void setMemberId(){
        // 在每个测试用例开始前 创建一个新的成员用于测试
        String id = "userId"+Thread.currentThread().getId()+FakeDataUtil.getCurrentTimeStamp();
        String name = "userName"+Thread.currentThread().getId()+FakeDataUtil.getCurrentTimeStamp();
        userId = MemberApi.getUserId(id, name, FakeDataUtil.getFakeMobile(), "2", token);

    }


    /**
     * 添加数据驱动进行测试
     * @param userId 用户ID
     * @param userName 用户名称
     * @param mobile 用户手机号
     * @param departmentId 部门ID
     */
    @Description("创建成员测试用例-测试驱动多参数执行")
    @Story("执行创建成员用例")
    @DisplayName("创建成员")
    @ParameterizedTest
    @CsvFileSource(resources="/member_data/add_member.csv", numLinesToSkip=1)
    @Order(1)
    public void addMemberTest(String userId,String userName,String mobile,String departmentId,String errCode,String errMsg){
        Response response = MemberApi.addMember(userId,userName,mobile,departmentId,token);
        // 使用软断言进行多个断言判断
        assertAll("添加成员断言",
                () -> assertThat(response.path("errcode").toString(), equalTo(errCode)),
                () -> assertThat(response.path("errmsg").toString(), containsString(errMsg))
        );
    }


    @Story("执行获取成员信息用例")
    @DisplayName("获取成员信息")
    @Test
    @Order(2)
    public void getMemberMesgTest(){
        Response response = MemberApi.getMemberMessage(token, userId);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Story("执行修改成员信息用例")
    @DisplayName("获取修改成员信息")
    @Test
    @Order(3)
    public void updateMemberTest(){
        Response response = MemberApi.updateMember(token, userId,"小三","2");
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Story("执行删除成员信息用例")
    @DisplayName("删除成员")
    @Test
    @Order(4)
    public void deleteMemberTest(){
        Response response = MemberApi.deleteMember(token, userId);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Story("执行批量删除成员信息用例")
    @DisplayName("批量删除成员")
    @Test
    @Order(5)
    public void deleteMemberListTest(){
        ArrayList<String> list = new ArrayList<>();
        list.add(userId);
        Response response = MemberApi.deleteMemberList(token, list);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }


    @Story("执行获取部门成员列表信息用例")
    @DisplayName("获取部门成员列表信息")
    @Test
    @Order(6)
    public void getDepartmentMemberListTest(){
        Response response = MemberApi.getDepartmentUserList(token,"1");
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }


    @Story("执行获取openId用例")
    @DisplayName("获取openId")
    @Test
    @Order(7)
    public void getOpenIdTest(){
        Response response = MemberApi.getOpenid(token, "ChenJinXuan");
        System.out.println(response.path("openid").toString());
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }


    @Story("执行校验用户信息用例")
    @DisplayName("校验用户信息")
    @Test
    @Order(8)
    public void checkInfoTest(){
        Response response = MemberApi.checkInfo(token, "ChenJinXuan");
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }

    @Story("执行邀请成员用例")
    @DisplayName("邀请成员")
    @Test
    @Order(9)
    public void inviteMemberTest(){
        ArrayList<String> userIds = new ArrayList<String>();
        userIds.add("ChenJinXuan");
        userIds.add("zhangsan99");
        ArrayList<Integer> partyIds = new ArrayList<Integer>();
        partyIds.add(1);
        Response response = MemberApi.inviteMember(token, userIds, partyIds);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }


    @Story("执行获取加入企业微信二维码用例")
    @DisplayName("获取加入企业微信二维码")
    @Test
    @Order(10)
    public void getJoinQrCode(){
        Response response = MemberApi.getJoinQrCode(token);
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }


    @Story("执行获取活跃用户用例")
    @DisplayName("获取活跃用户")
    @Test
    @Order(11)
    public void getActiveMember(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Response response = MemberApi.getActiveStat(token, dateFormat.format(date).toString());
        assertThat(response.path("errcode").toString(),equalTo("0"));
    }
}
