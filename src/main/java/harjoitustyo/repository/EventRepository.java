package harjoitustyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import harjoitustyo.entity.Events;

@Repository
public interface EventRepository extends JpaRepository<Events, Long> {
}
