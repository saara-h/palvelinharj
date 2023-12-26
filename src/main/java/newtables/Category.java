package newtables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Category extends AbstractPersistable <Long> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "category_name", nullable = false)
    private String categoryName;
}
