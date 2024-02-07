package com.ht.qlktx.modules.users;

import com.ht.qlktx.entities.User;
import com.ht.qlktx.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByRoleIsNot(Role role);

    Optional<User> findByIdAndRoleIsNot(String userId, Role role);

    boolean existsByEmail(String email);
}
