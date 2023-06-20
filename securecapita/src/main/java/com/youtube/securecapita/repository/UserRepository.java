package com.youtube.securecapita.repository;

import com.youtube.securecapita.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository <T extends User> {
    /* Basic CRUD operations */

    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(String id);
    T update(T data);
    Boolean delete(String id);

    /* More Complex Operations */

}
