package api_object;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * @author: JJJJ
 * @date:2021/4/2 18:04
 * @Description: 部门管理接口
 */
public class DepartmentApi {


    /**
     * 创建部门api
     * @param accessToken token
     * @param partyName 部门名称
     * @param enName    部门英文名
     * @param parentId  父部门ID
     * @param order     在父部门下的排序位置
     * @param id        部门id
     * @return
     */
    public static Response addParty(String accessToken,String partyName,String enName,String parentId,String order,String id){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken;
        String body = "{\n" +
                "   \"name\": \""+partyName+"\",\n" +
                "   \"name_en\": \""+enName+"\",\n" +
                "   \"parentid\": "+parentId+",\n" +
                "   \"order\": "+order+",\n" +
                "   \"id\": "+id+"\n" +
                "}";
        return addParty(accessToken,body);
    }

    /**
     * 不传部门id新增部门
     * @param accessToken token
     * @param partyName 部门名称
     * @param enName    部门英文名
     * @param parentId  父部门ID
     * @param order     在父部门下的排序位置
     * @return
     */
    public static Response addParty(String accessToken,String partyName,String enName,String parentId,String order){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken;
        String body = "{\n" +
                "   \"name\": \""+partyName+"\",\n" +
                "   \"name_en\": \""+enName+"\",\n" +
                "   \"parentid\": "+parentId+",\n" +
                "   \"order\": "+order+",\n" +
                "}";
        return addParty(accessToken,body);
    }

    /**
     * 只传必须的参数
     * @param accessToken token
     * @param partyName 部门名称
     * @param parentId 父部门Id
     * @return
     */
    public static Response addParty(String accessToken,String partyName,String parentId){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken;
        String body = "{\n" +
                "   \"name\": \""+partyName+"\",\n" +
                "   \"parentid\": "+parentId+",\n" +
                "}";
        return addParty(accessToken,body);
    }

    /**
     * 添加部门具体实现
     * @param accessToken token
     * @param body 请求体
     * @return
     */
    public static Response addParty(String accessToken,String body){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken;
        return given()
                .contentType("application/json")
                .body(body).post(url)
                .then().log().all()
                .extract().response();
    }

    /**
     * 修改部门api
     * @param accessToken token
     * @param partyId   部门id
     * @param newPartyName 新的部门名字
     * @return
     */
    public static Response updateParty(String accessToken,String partyId,String newPartyName){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken;
        String body = "{\n" +
                "   \"id\": 2,\n" +
                "   \"name\": \"广州研发中心\",\n" +
                "}";
        return given()
                .contentType("application/json")
                .body(body).post(url)
                .then().log().all()
                .extract().response();
    }

    /**
     * 删除部门api
     * @param accessToken token
     * @param partyId 部门id
     * @return
     */
    public static Response deleteParty(String accessToken,String partyId){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete";
        return given()
                .queryParams("access_token",accessToken,"id",partyId)
                .get(url)
                .then().log().all()
                .extract().response();
    }

    /**
     * 获取部门下的子部门列表
     * @param accessToken token
     * @param id 部门id
     * @return
     */
    public static Response getPartyList(String accessToken,String partyId){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list";
        return given()
                .queryParams("access_token",accessToken,"id",partyId)
                .get(url)
                .then().log().all()
                .extract().response();
    }


}
