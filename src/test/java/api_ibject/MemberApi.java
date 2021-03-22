package api_ibject;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * ClassName: MemberApi
 * date: 2021/3/22 16:49
 *
 * @author JJJJ
 * Description: member管理相关api
 */
public class MemberApi {


    /**
     * 添加成员
     * @param userId 用户ID
     * @param userName 用户姓名
     * @param mobile 手机好
     * @param departmentId 用户所属部门id
     * @param accessToken token
     * @return
     */
    public static Response addMember(String userId,String userName,String mobile,String departmentId,String accessToken){
        String body =
                "{\n" +
                        "    \"userid\": \"" + userId + "\",\n" +
                        "    \"name\": \"" + userName + "\",\n" +
                        "    \"mobile\": \"+86 " + mobile + "\",\n" +
                        "    \"department\": [" + departmentId + "],\n" +
                        "}";
        return given()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token="+accessToken)
                .then().log().all()
                .extract().response();
    }

    /**
     * 获取成员信息
     * @param accessToken token
     * @param userId 用户ID
     * @return
     */
    public static Response getMemberMessage(String accessToken,String userId){
        return given()
                .contentType("application/json")
                .queryParams("access_token",accessToken,"userid",userId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
                .then().log().all()
                .extract().response();
    }


    public static Response updateMember(String accessToken,String userId,String name,String departmentId){
        String body = "{\n" +
                "    \"userid\": \""+userId+"\",\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"department\": ["+departmentId+"],\n" +
                "}";
        return given()
                .contentType("application/json")
                .body(body)
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token="+accessToken)
                .then()
                .log().all()
                .extract().response();
    }
}
