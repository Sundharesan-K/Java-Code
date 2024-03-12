package com.app.Instagram2K24.service;

import com.app.Instagram2K24.dao.AdminDao;
import com.app.Instagram2K24.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Optional<Admin> admin = Optional.ofNullable(adminDao.findAdmin(emailId));
        return admin.map(UserUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("admin not found " + emailId));
    }
}
