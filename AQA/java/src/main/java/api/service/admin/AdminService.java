package api.service.admin;

import api.service.BaseApi;
import api.util.assertions.AssertableResponse;

import java.util.Map;

public class AdminService extends BaseApi {

    public AssertableResponse getCollections(String token) {
        return new AssertableResponse(setupAdminSpec(token)
                .get(AdminEndPoint.GET_COLLECTIONS));
    }

    public AssertableResponse getUsers(Map<String, Object> params, String token) {
        return new AssertableResponse(setupAdminSpec(token)
                .params(params)
                .get(AdminEndPoint.GET_USERS));
    }
}
