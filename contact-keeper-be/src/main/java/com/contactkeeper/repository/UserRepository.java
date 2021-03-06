package com.contactkeeper.repository;

import com.contactkeeper.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String user);
    User findByEmail(String email);
}
