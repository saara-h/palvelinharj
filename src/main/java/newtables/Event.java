package newtables;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event extends AbstractPersistable<Long> {

    private String eventTitle;
    private String eventDescription;
    private Date eventDate;
    private Time eventTime;
    private Date dueDate;
    
}
