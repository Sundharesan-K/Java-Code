package com.trustrace.mongodbSpringControl.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final MongoTemplate template;

    public List<Product> searchByName(String name) {
        Query query=new Query ();
        query.addCriteria (Criteria.where ("name").is (name));
        List<Product> products=template.find (query,Product.class);
        return products;
    }

    public List<Product> searchByNameStartsWith(String name) {
        Query query=new Query ();
        query.addCriteria (Criteria.where ("name").regex ("^"+name));
        List<Product> products=template.find (query,Product.class);
        return products;
    }

    public List<Product> searchByNameEndsWith(String name) {
        Query query=new Query ();
        query.addCriteria (Criteria.where ("name").regex (name+"$"));
        List<Product> products=template.find (query,Product.class);
        return products;
    }

    public List<Product> searchByPriceLt(BigDecimal price) {
        Query query=new Query ();
        query.addCriteria (Criteria.where ("price").lte(price));
        List<Product> products=template.find (query,Product.class);
        return products;
    }
    public List<Product> searchByPriceGt(BigDecimal price) {
        Query query=new Query ();
        query.addCriteria (Criteria.where ("price").gt(price));
        List<Product> products=template.find (query,Product.class);
        return products;
    }
}
