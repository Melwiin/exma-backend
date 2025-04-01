package de.melwink.exma_backend.repository;

import de.melwink.exma_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
