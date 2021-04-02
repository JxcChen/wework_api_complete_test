package api_object;

import static io.restassured.RestAssured.given;

/**
 * ClassName: GetToken
 * date: 2021/3/22 16:12
 *
 * @author JJJJ
 * Description: 用于获取token
 */
public class GetToken {

    /**
     * 获取token
     * @param corpid 企业ID
     * @param corpsecret 应用密钥
     * @return
     */
    public static String getToken(String corpid,String corpsecret){
        // https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET
        return given()
                .formParams("corpid",corpid,"corpsecret",corpsecret)
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract()
                .path("access_token");
    }

}
