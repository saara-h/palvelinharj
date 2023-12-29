package harjoitustyo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Categories extends AbstractPersistable <Long> {

    @Column(name = "category_name")
    private String categoryName;

}

