package com.trustrace.Switchenergysystembackend.repository;

import com.trustrace.Switchenergysystembackend.entity.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends MongoRepository<Provider,String> {

    Provider findByName(String name);
}
