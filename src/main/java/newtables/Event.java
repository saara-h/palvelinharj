package newtables;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Event extends AbstractPersistable<Long> {

    private String eventTitle;
    private String eventDescription;
    private Date eventDate;
    private Time eventTime;
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
}
