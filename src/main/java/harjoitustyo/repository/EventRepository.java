package harjoitustyo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import harjoitustyo.entity.Categories;
import harjoitustyo.entity.Events;

@Repository
public interface EventRepository extends JpaRepository<Events, Long> {
    List<Events> findAllByOrderByEventDateAsc();
    List<Events> findByCategories(Categories category);
}
