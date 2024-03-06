package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service.impl;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.AdminDao;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.AdminDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ProviderDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Admin;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Provider;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String adminLogin(AdminDto adminDto) throws Exception {
        Admin admin = adminDao.findAdmin(adminDto);
        try {
            if (admin != null) {
                System.out.println(passwordEncoder.encode(adminDto.getPassword()) + "---" + admin.getPassword());
                String encode = passwordEncoder.encode(admin.getPassword());
                if (passwordEncoder.matches(adminDto.getPassword(), encode)) {
                    return "Logged in successfully";
                } else {
                    throw new Exception("Password is incorrect");
                }
            } else {
                throw new Exception("Username is incorrect");
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public void addAdmin(AdminDto adminDto) throws Exception {
        if (adminDao.findAdmin(adminDto) == null) {
            Admin admin = new Admin();
            admin.setUsername(adminDto.getUsername());
            admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
            try {

                adminDao.addAdmin(admin);
            } catch (Exception e) {
                throw new Exception("Internal server error");
            }
        } else {
            throw new Exception("Username already exists");
        }
    }

    @Override
    public void addProvider(ProviderDto providerDto) throws Exception {
        try {
            if (adminDao.findProvider(providerDto.getName()) == null) {
                Provider provider = new Provider();
                provider.setName(providerDto.getName());
                provider.setRate(providerDto.getRate());
                provider.setActive(true);
                adminDao.addProvider(provider);
            } else {
                throw new Exception("Provider already exists");
            }
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    @Override
    public List<Provider> getAllProvider() {
        return adminDao.getAllProviders();
    }

    @Override
    public void changeProviderStatus(String provideName, Boolean active) throws Exception {
        try {
            adminDao.changeStatus(provideName, active);
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminDao.getALlAdmins();
    }
}
