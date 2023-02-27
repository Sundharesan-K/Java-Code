package com.trustrace.security30.service;

import com.trustrace.security30.pojo.Provider;
import com.trustrace.security30.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {
    @Autowired
    private ProviderRepository repository;

    public String create(Provider provider) {
        Provider provider1=repository.findByName(provider.getName ());
        if (provider1 != null){
            throw new RuntimeException ("Provider already exists");
        }
        provider.setStatus ("enabled");
        return repository.save(provider);
    }

    public String getAllProviders() {
        return repository.findAll();
    }
}
