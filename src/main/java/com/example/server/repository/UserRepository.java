package com.example.server.repository;

import com.example.server.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Author       xinliu
 * Date         6/29/17
 * Time         7:05 PM
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByAccount(String account);

}
