package newtables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class User extends AbstractPersistable <Long> {

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Event> events = new ArrayList <>();
    
}
