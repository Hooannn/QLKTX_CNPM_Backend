package com.ht.qlktx.modules.users;

import com.ht.qlktx.entities.User;
import com.ht.qlktx.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByRoleIsNot(Role role);

    Optional<User> findByIdAndRoleIsNot(String userId, Role role);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role != 'ADMIN' and (u.id LIKE %:keyword% OR concat(u.firstName, ' ', u.lastName) LIKE %:keyword%)")
    List<User> lookUpByIdOrName(String keyword);

    List<User> findAllByRoleIs(Role role);

    Optional<User> findByIdAndRoleIs(String studentId, Role role);

    @Query("SELECT u FROM User u WHERE u.role != 'ADMIN' and u.role = 'STUDENT' and (u.id LIKE %:keyword% OR concat(u.firstName, ' ', u.lastName) LIKE %:keyword%)")
    List<User> lookupStudentsByIdOrName(String keyword);
}
