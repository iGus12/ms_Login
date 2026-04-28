package com.example.ms_login.interfaces;

import com.example.ms_login.dto.LoginRequest;
import com.example.ms_login.dto.LoginResponse;

public interface ILoginService {
    LoginResponse login(LoginRequest request);
}