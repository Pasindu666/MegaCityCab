package com.pasindu.MegaCityCab.service.interfac;

import com.pasindu.MegaCityCab.dto.LoginRequest;
import com.pasindu.MegaCityCab.dto.Response;
import com.pasindu.MegaCityCab.entity.User;

public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

}