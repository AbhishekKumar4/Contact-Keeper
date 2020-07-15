package com.contactkeeper.repository;

import com.contactkeeper.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepo extends CrudRepository<User, Long> {
    public User findByName(String user);
}
