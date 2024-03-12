package com.app.Instagram2K24.service.impl;

import com.app.Instagram2K24.DataUtil;
import com.app.Instagram2K24.dao.AdminDao;
import com.app.Instagram2K24.dto.NotificationEmail;
import com.app.Instagram2K24.model.Admin;
import com.app.Instagram2K24.service.AdminService;
import com.app.Instagram2K24.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.app.Instagram2K24.constant.BasicConstant.*;
import static com.app.Instagram2K24.constant.ConstantList.ADMIN;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Override
    public String createAdmin(Admin admin) throws Exception {
        boolean validEmailString = DataUtil.isValidEmailString(admin.getEmailId());
        try {
            if (validEmailString) {
                if (admin.getRole().equals(ADMIN.name())) {
                    if (adminDao.findAdmin(admin.getEmailId()) == null) {
                        if (admin.getPassword().length() >= 6) {
                            passwordEncoder.encode(admin.getPassword());
                            adminDao.createAdmin(admin);
                            mailService.sendMail(new NotificationEmail("Admin Activated",admin.getEmailId(),"Thank your activated in admin role"));
                            return ADMIN_CREATE_SUCCESS;
                        } else {
                            throw new Exception(PASSWORD_LIMIT_MESSAGE);
                        }
                    } else {
                        throw new Exception(ADMIN_ALREADY_EXISTS_MESSAGE);
                    }
                } else {
                    throw new Exception(ALLOWED);
                }
            }else {
                throw new Exception(VALID_EMAIL);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String adminLogin(Admin admin) throws Exception {
        Admin admin1 = adminDao.findAdmin(admin.getEmailId());
        try {
            if (Objects.nonNull(admin1)){
                System.out.println(passwordEncoder.encode(admin.getPassword())+"-----"+admin1.getPassword());
                String encode = passwordEncoder.encode(admin.getPassword());
                if (passwordEncoder.matches(admin1.getPassword(),encode)){
                    return LOGIN_MESSAGE;
                }else {
                    throw new Exception(INCORRECT_PASSWORD);
                }
            }else {
                throw new Exception(INCORRECT_EMAILID);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
