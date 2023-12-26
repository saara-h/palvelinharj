package newtables;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Categories extends AbstractPersistable <Long> {

    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
