package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.AdminDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ProviderDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Admin;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Provider;

import java.util.List;

public interface AdminDao {
    Admin findAdminByUsername(String username);

    Admin findAdmin(AdminDto adminDto);

    void addAdmin(Admin admin);

    Provider findProvider(String providerName);

    void addProvider(Provider provider);

    List<Provider> getAllProviders();

    void changeStatus(String provideName, Boolean active);

    List<Admin> getALlAdmins();
}
