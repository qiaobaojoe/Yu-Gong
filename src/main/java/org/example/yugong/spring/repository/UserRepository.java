package org.example.yugong.spring.repository;

import org.example.yugong.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author qiaobao
 * @since 2021-03-04
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select t from User t where t.name = :name")
    User findByUserName(@Param("name") String name);
}
