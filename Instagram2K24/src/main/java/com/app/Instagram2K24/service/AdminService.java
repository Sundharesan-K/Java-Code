package com.app.Instagram2K24.service;

import com.app.Instagram2K24.model.Admin;

public interface AdminService {
    String createAdmin(Admin admin) throws Exception;

    String adminLogin(Admin admin) throws Exception;
}
