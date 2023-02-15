package com.trustrace.Switchenergysystembackend.service;

import com.trustrace.Switchenergysystembackend.entity.Provider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProviderService {
    Provider createProvider(Provider provider) ;

    List<Provider> getAllProvider();

    Provider changeStatus(String name, String status);
}
