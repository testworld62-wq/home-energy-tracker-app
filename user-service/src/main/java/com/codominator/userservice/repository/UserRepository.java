package com.codominator.userservice.repository;

import com.codominator.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
