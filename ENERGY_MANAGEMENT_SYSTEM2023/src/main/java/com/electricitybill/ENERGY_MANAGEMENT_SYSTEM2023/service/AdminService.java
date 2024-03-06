package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.AdminDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ProviderDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Admin;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Provider;

import java.util.List;

public interface AdminService {
    String adminLogin(AdminDto adminDto)throws Exception;

    void addAdmin(AdminDto adminDto) throws Exception;

    void addProvider(ProviderDto providerDto) throws Exception;

    List<Provider> getAllProvider();

    void changeProviderStatus(String provideName, Boolean active) throws Exception;

    List<Admin> getAllAdmins();
}
