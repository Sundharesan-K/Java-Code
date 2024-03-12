package com.app.Instagram2K24.dao;

import com.app.Instagram2K24.model.Admin;

public interface AdminDao {
     Admin findAdmin(String emailId);

    void createAdmin(Admin admin);
}
