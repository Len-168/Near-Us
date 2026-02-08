package app.near.us.api_security.service;

import app.near.us.api_security.dto.LoginReq;
import app.near.us.api_security.dto.LoginRes;
import app.near.us.api_security.dto.RegisterReq;
import app.near.us.api_security.dto.RegisterRes;
import app.near.us.common.ApiResponse;

public interface AuthenticationService {
    ApiResponse<RegisterRes> register(RegisterReq req);
    ApiResponse<LoginRes> login(LoginReq req);
}
