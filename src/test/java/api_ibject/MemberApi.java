package api_ibject;

import io.restassured.response.Response;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.ArrayList;

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
     *
     * 获取新增成员的 用户id
     * @param userId 用户ID
     * @param userName 用户姓名
     * @param mobile 手机好
     * @param departmentId 用户所属部门id
     * @param accessToken token
     * @return 用户id
     */
    public static String getUserId(String userId, String userName, String mobile, String departmentId, String accessToken){
        Response response = addMember(userId, userName, mobile, departmentId, accessToken);
        return userId;
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

    /**
     * 修改用户信息
     * @param accessToken token
     * @param userId 用户ID
     * @param name 用户姓名
     * @param departmentId 部门ID
     * @return
     */
    public static Response updateMember(String accessToken,String userId,String name,String departmentId){
        String body = "{\n" +
                "    \"userid\": \"" + userId + "\",\n" +
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

    /**
     * 删除用户接口
     * @param accessToken token
     * @param userId 用户id
     * @return response
     */
    public static Response deleteMember(String accessToken, String userId){
        // https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID
        return given()
                .queryParams("access_token",accessToken,"userid",userId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then().log().all()
                .extract().response();

    }


    /**
     * 批量删除成员
     * @param accessToken token
     * @param userIdList 用户ID列表
     * @return
     */
    public static Response deleteMemberList(String accessToken, ArrayList<String> userIdList){
        // https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN
        String userIdListStr = "[";
        int size = userIdList.size();
        if(size > 0){
            // 对body进行拼接
            for (int i = 0; i < userIdList.size(); i++) {
                if (i == userIdList.size() - 1) {
                    userIdListStr += "\"" + userIdList.get(i) + "\"]";
                    break;
                }
                userIdListStr += "\"" + userIdList.get(i) + "\",";
            }
            String body = "{\n" +
                    "   \"useridlist\": " + userIdListStr + "\n" +
                    "}";
            return given()
                    .contentType("application/json")
                    .body(body)
                    .post("https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token="+accessToken)
                    .then().log().all()
                    .extract().response();
        }
       return null;
    }

    /**
     * 获取部门成员列表
     * @param accessToken token
     * @param departmentId 部门ID
     * @return
     */
    public static Response getDepartmentUserList(String accessToken,String departmentId){
        // https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID
        return given()
                .queryParams("access_token",accessToken,"department_id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist")
                .then().log().all()
                .extract().response();
    }

    /**
     * 获取部门成员id列表  用户前置删除成员数据
     * @param accessToken token
     * @param departmentId 部门ID
     * @return 部门钟所有成员的id列表
     */
    public static ArrayList<String> getDepartmentUserIdList(String accessToken, String departmentId){
        ArrayList<String> userIdList = getDepartmentUserList(accessToken,departmentId).path("userlist.userid");
        return userIdList;
    }

    /**
     * 交换openId
     * @param accessToken token
     * @param userId 用户id  只能是有绑定微信的id
     * @return
     */
    public static Response getOpenid(String accessToken,String userId){
        // https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token=ACCESS_TOKEN
        String body = "{\n" +
                "   \"userid\": \""+userId+"\"\n" +
                "}";
        return given()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token="+accessToken)
                .then().log().all()
                .extract().response();
    }


    /**
     * 对成员而此校验
     * @param accessToken token
     * @param userId 用户id
     * @return
     */
    public static Response checkInfo(String accessToken,String userId){
        // https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token=ACCESS_TOKEN&userid=USERID
        return given()
                .queryParams("access_token",accessToken,"userid",userId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/authsucc")
                .then().log().all()
                .extract().response();
    }


    /**
     * 邀请成员
     * @param accessToken token
     * @param userId 用户ID列表
     * @param partyId 部门ID列表
     * @return
     */
    public static Response inviteMember(String accessToken,ArrayList<String> userId,ArrayList<Integer> partyId){
        //  https://qyapi.weixin.qq.com/cgi-bin/batch/invite?access_token=ACCESS_TOKEN
        String userIdStr = "[";
        for(int i = 0;i < userId.size();i++){
            if(i == userId.size()-1){
                userIdStr += "\""+userId.get(i)+"\"]";
            }
            userIdStr += "\""+userId.get(i)+"\",";
        }
        String partyIdStr = "[";
        for(int i = 0;i < partyId.size();i++){
            if(i == partyId.size()-1){
                partyIdStr += "\""+partyId.get(i)+"\"]";
            }
            partyIdStr += "\""+partyId.get(i)+"\",";
        }
        String body = "{\n" +
                "   \"user\" : "+userIdStr+",\n" +
                "   \"party\" : "+partyIdStr+",\n" +
                "}";
        return given()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/batch/invite?access_token="+accessToken)
                .then().log().all()
                .extract().response();
    }


    /**
     * 获取加入企业微信二维码
     * @param accessToken  token
     * @return
     */
    public static Response getJoinQrCode(String accessToken){
        return given()
                .queryParams("access_token",accessToken)
                .get("https://qyapi.weixin.qq.com/cgi-bin/corp/get_join_qrcode")
                .then().log().all()
                .extract().response();
    }

    /**
     * 获取当前活跃的成员
     * @param accessToken token
     * @param data 当前日期
     * @return
     */
    public static Response getActiveStat(String accessToken,String data){
        // https://qyapi.weixin.qq.com/cgi-bin/user/get_active_stat?access_token=ACCESS_TOKEN
        String body = "{\n" +
                "    \"date\": \""+data+"\"\n" +
                "}";
        return given()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/get_active_stat?access_token="+accessToken)
                .then().log().all()
                .extract().response();
    }

}
