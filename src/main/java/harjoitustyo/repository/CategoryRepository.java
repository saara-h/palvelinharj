package harjoitustyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import harjoitustyo.entity.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
}