package api.service.admin;

import api.service.BaseApi;
import api.util.assertions.AssertableResponse;

public class AdminService extends BaseApi {

    public AssertableResponse getCollections(String token) {
        return new AssertableResponse(setupAdminSpec(token)
                .get(AdminEndPoint.GET_COLLECTIONS));
    }

    public AssertableResponse getUsers(String token) {
        return new AssertableResponse(setupAdminSpec(token)
                .get(AdminEndPoint.GET_USERS));
    }
}
