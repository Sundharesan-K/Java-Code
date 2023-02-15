package com.trustrace.Switchenergysystembackend.service.impl;

import com.trustrace.Switchenergysystembackend.entity.Provider;
import com.trustrace.Switchenergysystembackend.repository.ProviderRepository;
import com.trustrace.Switchenergysystembackend.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Provider createProvider(Provider provider) {
        Provider providerRepoByName=providerRepository.findByName(provider.getName ());
        if (providerRepoByName!=null){
            throw new RuntimeException ("Provider already exists");
        }
        provider.setStatus ("enabled");
        return providerRepository.save (provider);
    }

    @Override
    public List<Provider> getAllProvider() {
        return providerRepository.findAll ();
    }

    @Override
    public Provider changeStatus(String name, String status) {
        Provider provider=providerRepository.findByName (name);
        if (provider==null){
            throw new RuntimeException ("Provider Not Found");
        }
        provider.setStatus (status);
        return providerRepository.save (provider);
    }
}
