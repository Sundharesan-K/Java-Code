package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.impl;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.AdminDao;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.AdminDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Admin;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDaoImpl implements AdminDao {
    @Autowired
    private MongoTemplate template;

    @Override
    public Admin findAdminByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return template.findOne(query, Admin.class);
    }

    @Override
    public Admin findAdmin(AdminDto adminDto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(adminDto.getUsername()));
        return template.findOne(query, Admin.class);
    }

    @Override
    public void addAdmin(Admin admin) {
        template.insert(admin);
    }

    @Override
    public Provider findProvider(String providerName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(providerName));
        return template.findOne(query, Provider.class);
    }

    @Override
    public void addProvider(Provider provider) {
        template.insert(provider);
    }

    @Override
    public List<Provider> getAllProviders() {
        return template.findAll(Provider.class);
    }

    @Override
    public void changeStatus(String provideName, Boolean status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(provideName));
        Update update = new Update();
        update.set("active", status);
        template.updateFirst(query, update, Provider.class);
    }

    @Override
    public List<Admin> getALlAdmins() {
        return template.findAll(Admin.class);
    }
}
