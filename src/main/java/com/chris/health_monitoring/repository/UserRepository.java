// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.chris.health_monitoring.repository;

import com.chris.health_monitoring.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   boolean existsByUsername(String username);

   boolean existsByEmail(String email);

   Optional<User> findByUsername(String username);

   Optional<User> findByEmail(String email);
}
