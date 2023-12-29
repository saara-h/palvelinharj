package harjoitustyo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import harjoitustyo.entity.Categories;
import harjoitustyo.repository.CategoryRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

        @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void initializeCategories() {
        // Check if categories exist, if not, initialize them
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Categories("Category 1"));
            categoryRepository.save(new Categories("Category 2"));
            categoryRepository.save(new Categories("Category 3"));
        }
    }
}
