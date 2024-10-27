package ru.yaotone.hibernateproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yaotone.hibernateproj.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
