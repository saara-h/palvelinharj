package newtables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Categories extends AbstractPersistable <Long> {

    private User user;
    private String categoryName;

    
}
