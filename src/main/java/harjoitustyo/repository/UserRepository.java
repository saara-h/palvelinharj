package harjoitustyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import harjoitustyo.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
